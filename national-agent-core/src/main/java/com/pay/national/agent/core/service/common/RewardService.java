package com.pay.national.agent.core.service.common;

import java.util.Date;
import java.util.List;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardSummaryResultBean;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.entity.RewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

/**
 * 奖励服务
 * @author shuyan.qi
 * Date:2017年9月12日上午2:39:32
 */
public interface RewardService {
	/**
	 * 创建奖励记录
	 * @param rewardRecord
	 * @return
	 */
	RewardRecord createRewardRecord(RewardRecord rewardRecord);
	
	/**
	 * 奖励操作
	 * @param rewardRecord
	 * @return
	 */
	ReturnBean<String> reward(RewardRecord rewardRecord);

	/**
	 * 日奖励汇总
	 * @param yesterday
	 */
	void gatherForDay(Date date);

	/**
	 * 汇总奖励总金额记录
	 * @param yesterday
	 */
	void gatherForAll(Date date);
	
	/**
	 * 获取奖励规则列表
	 * @param childBusinessCode
	 * @return
	 */
	List<RewardRule> getRewardRules(ChildBusinessCode childBusinessCode);

	/**
	 * 获取奖励记录
	 * @param userNo 用户编号
	 * @param orderNo 订单编号
	 * @param ruleId 奖励规则id
	 * @return
	 */
	RewardRecord getRewardRecord(String userNo,String orderNo,Integer ruleId);

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
	ReturnBean<List<RewardDayResultBean>> rewardSummaryDay(String userNo, String startDate, String endDate,
			BusinessCode businessCode);
}
