package com.pay.national.agent.core.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.facade.RewardFacade;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardSummaryResultBean;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.enums.BusinessCode;

@Service("rewardFacade")
public class RewardFacadeImpl implements RewardFacade{
	
	@Resource
	private RewardService rewardService;

	@Override
	public ReturnBean<String> reward(RewardRecord rewardRecord) {
		return rewardService.reward(rewardRecord);
	}

	/**
	 * 业务奖励月汇总列表
	 * @param userNo 用户编号
	 * @param businessCode 业务编码
	 * @return
	 */
	@Override
	public ReturnBean<RewardSummaryResultBean> rewardSummaryMonth(String userNo, BusinessCode businessCode) {
		return rewardService.rewardSummaryMonth(userNo, businessCode);
	}
	
	/**
	 * 业务奖励日汇总列表
	 * @param userNo
	 * @param startDate 格式（yyyy-MM-dd）
	 * @param endDate 格式（yyyy-MM-dd）
	 * @param businessCode
	 * @return
	 */
	@Override
	public ReturnBean<List<RewardDayResultBean>> rewardSummaryDay(String userNo, String startDate,
			String endDate, BusinessCode businessCode) {
		return rewardService.rewardSummaryDay(userNo,startDate,endDate,businessCode);
	}

}
