package com.pay.national.agent.core.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.core.service.credit.CreditCardService;
import com.pay.national.agent.model.beans.CreditCardOrderMatchBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.entity.CreditCardUser;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.entity.RewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.CreditCardOrderStatus;
import com.pay.national.agent.model.enums.CreditCardUserStatus;
import com.pay.national.agent.model.enums.RewardStatus;
/**
 * 信用卡业务奖励发放定时任务
 * @author shuyan.qi
 * Date:2017年9月28日上午3:46:56
 */
@Component
public class CreditCardRewardJob {//extends AbstractJob{
	@Resource
	private CreditCardService creditCardService;
	@Resource
	private RewardService rewardService;

	//@Override
	public void execute() {
		//1.查询还未奖励的信用卡订单数据
		List<CreditCardOrderMatchBean> list = creditCardService.matchOrder();
		if(null != list && !list.isEmpty()){
			for (CreditCardOrderMatchBean matchBean : list) {
				reward(matchBean);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	private void reward(CreditCardOrderMatchBean matchBean) {
		//2.查询信用卡奖励规则
		CreditCardOrder order = matchBean.getOrder();
		List<RewardRule> rewardRules = rewardService.getRewardRules(order.getBusinessCode());
		if(null != rewardRules && rewardRules.size() > 0){
			RewardRecord rewardRecord = rewardService.getRewardRecord(order.getUserNo(), order.getOrderNo(), rewardRules.get(0).getId());
			if(null != rewardRecord  && rewardRecord.getRewardStatus() == RewardStatus.SUCCESS){
				return;
			}else{
				if(null == rewardRecord){
					rewardRecord = new RewardRecord();
					rewardRecord.setBusinessCode(BusinessCode.CREDIT_CARD);
					rewardRecord.setChildBusinessCode(order.getBusinessCode());
					rewardRecord.setCreateTime(new Date());
					rewardRecord.setOrderNo(order.getOrderNo());
					rewardRecord.setRewardAmount(rewardRules.get(0).getRewardAmount());
					rewardRecord.setStatus("INIT");
					rewardRecord.setUserNo(order.getUserNo());
					rewardRecord.setRuleId(rewardRules.get(0).getId());
				}
				
				//3.奖励入账
				ReturnBean<String> returnBean = rewardService.reward(rewardRecord);
				
				if(RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
					//4.更新信用卡订单状态
					order.setStatus(CreditCardOrderStatus.SUCCESS);
					order.setLastUpdateTime(new Date());
					creditCardService.updateOrder(order);
					
					//5.更新信用卡用户状态
					CreditCardUser creditCardUser = creditCardService.findCreditCardUser(matchBean.getCreditCardUserId());
					creditCardUser.setStatus(CreditCardUserStatus.REWARD_SUCCESS);
					creditCardUser.setLastUpdateTime(new Date());
					creditCardService.updateCreditCardUser(creditCardUser);
				}
			}
		}
	}
}
