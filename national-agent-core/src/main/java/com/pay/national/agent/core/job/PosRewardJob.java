package com.pay.national.agent.core.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.core.service.pos.PosService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.PosOrder;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.entity.RewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.PosOrderStatus;
import com.pay.national.agent.model.enums.RewardStatus;
/**
 * pos业务奖励发放定时任务
 * @author shuyan.qi
 * Date:2017年10月16日上午4:03:53
 */
@Component
public class PosRewardJob {//extends AbstractJob{
	@Resource
	private PosService posService;
	@Resource
	private RewardService rewardService;
	/*@Resource
	private CustomerTransDaySyncFacade customerTransDaySyncFacade;*/

	//@Override
	public void execute() {
		//1.查询所有状态为‘TRUE’的pos业务订单
		List<PosOrder> orders = posService.findTrueOrders();
		if(null != orders && orders.size() > 0){
			for (PosOrder posOrder : orders) {
				reward(posOrder);
			}
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	private void reward(PosOrder posOrder) {
		//2.查询商户的交易金额
		Double amount = 0.00;//customerTransDaySyncFacade.getTransDayAmountTotal(posOrder.getCustomerNo(), posOrder.getCreateTime(), posOrder.getInvalidTime());
		//3.查询业务奖励规则
		List<RewardRule> rules = rewardService.getRewardRules(posOrder.getBusinessCode());
		if(null != rules && rules.size() >0){
			for (RewardRule rule : rules) {
				RewardRecord rewardRecord = null;
				if(null != amount && amount >= rule.getTransAmount()){
					rewardRecord = rewardService.getRewardRecord(posOrder.getUserNo(),posOrder.getOrderNo(),rule.getId());
					if(null != rewardRecord && rewardRecord.getRewardStatus() == RewardStatus.SUCCESS){
						return;
					}else{
						if(null == rewardRecord){
							rewardRecord = new RewardRecord();
							rewardRecord.setBusinessCode(BusinessCode.POS);
							rewardRecord.setChildBusinessCode(posOrder.getBusinessCode());
							rewardRecord.setCreateTime(new Date());
							rewardRecord.setOrderNo(posOrder.getOrderNo());
							rewardRecord.setRewardAmount(rule.getRewardAmount());
							rewardRecord.setStatus("INIT");
							rewardRecord.setUserNo(posOrder.getUserNo());
							rewardRecord.setRuleId(rule.getId());
						}
						//4.进行奖励
						ReturnBean<String> returnBean = rewardService.reward(rewardRecord);
						
						//5.更新pos业务订单状态
						if(RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
							Double rewardAmount = posOrder.getRewardAmount() + rule.getRewardAmount();
							posOrder.setRewardAmount(rewardAmount);
							if(rewardAmount >= posOrder.getRewardAmountTotal()){
								posOrder.setStatus(PosOrderStatus.END);
							}
							posService.updatePosOrder(posOrder);
						}
					}
					
				}
			}
		}
	}

}
