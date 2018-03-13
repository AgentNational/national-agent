package com.pay.national.agent.core.job;

import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.RewardRecordMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.model.beans.query.DepositParam;
import com.pay.national.agent.model.beans.results.DepositBean;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 计算上级奖励情况
 */
@Component
public class UpperRewardJob {


    /**
     * 奖励入账相关
     */
    @Resource
    private RewardRecordMapper rewardRecordMapper;

    @Resource
    private UserService userService;

    @Resource
    private AccountService accountService;

    /**
     * 定时发放上级奖励 每日凌晨一点跑
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute(){
        LogUtil.info("计算上级奖励定时开始。。");
        Date yesterday = DateUtil.getFixedDays(new Date(), -1);
        try {
            //查询所有的昨日奖励信息
            List<RewardRecord> rewardRecords = rewardRecordMapper.findYesterdayRewardRecord(yesterday);
            for(RewardRecord rewardRecord : rewardRecords){
                //查找上级为谁
                AppUser appUser = userService.findUserInfo(rewardRecord.getUserNo());
                if(StringUtils.isNotBlank(appUser.getParentUserNo())){
                    //入账
                    Double amount = rewardRecord.getAmount()*0.05;
                    DepositParam depositParam = new DepositParam();
                    depositParam.setAccountNo(appUser.getUserNo());
                    depositParam.setBusinessCode(BusinessCode.COMMISSION.name());
                    depositParam.setParentBusinessCode(ParentBusinessCode.NATIONAL_AGENT.name());
                    depositParam.setAmount(amount);
                    depositParam.setUserNo(appUser.getParentUserNo());
                    DepositBean depositBean = accountService.deposit(depositParam);


                    //生成奖励记录
                    RewardRecord rewardRecordDb = new RewardRecord();
                    rewardRecord.setStatus(StatusConstants.SUCCESS);
                    rewardRecord.setStatus(depositBean.getResult());
                    rewardRecord.setUserNo(appUser.getParentUserNo());
                    rewardRecord.setLowerUserNo(appUser.getUserNo());
                    rewardRecord.setAmount(amount);
                    rewardRecord.setBusinessCode(BusinessCode.COMMISSION.name());
                    rewardRecord.setParentBusinessCode(ParentBusinessCode.NATIONAL_AGENT.name());
                    rewardRecord.setCreateTime(new Date());
                    rewardRecord.setRewardTime(new Date());

                    rewardRecordMapper.insert(rewardRecordDb);
                }

            }
            LogUtil.info("计算上级奖励正常结束。。");
        }catch (Exception e){
            LogUtil.error("计算上级奖励定时异常。。",e);
        }

    }

}
