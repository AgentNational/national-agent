package com.pay.national.agent.core.job;

import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.RewardRecordMapper;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.model.entity.RewardRecord;
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
                //入账

            }
            LogUtil.info("计算上级奖励正常结束。。");
        }catch (Exception e){
            LogUtil.error("计算上级奖励定时异常。。",e);
        }

    }

}
