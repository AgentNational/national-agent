package com.pay.national.agent.core.facade;

import java.util.List;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardSummaryResultBean;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.enums.BusinessCode;

public interface RewardFacade {

	ReturnBean<String> reward(RewardRecord rewardRecord);

	/**
	 * 业务奖励月汇总列表
	 * @param userNo 用户编号
	 * @param businessCode 业务编码
	 * @return
	 */
	ReturnBean<RewardSummaryResultBean> rewardSummaryMonth(String userNo, BusinessCode businessCode);

	/**
	 * 业务奖励日汇总列表
	 * @param userNo
	 * @param startDate 格式（yyyy-MM-dd）
	 * @param endDate 格式（yyyy-MM-dd）
	 * @param businessCode
	 * @return
	 */
	ReturnBean<List<RewardDayResultBean>> rewardSummaryDay(String userNo, String monthFirstDay, String monthLastDay,
			BusinessCode valueOf);
}
