package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardMonthResultBean;
import com.pay.national.agent.model.entity.RewardGatherDay;

public interface RewardGatherDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardGatherDay record);

    RewardGatherDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RewardGatherDay record);
	
	void gather(String day);

	List<RewardGatherDay> selectByDate(String day);

	
	/**
     * 业务奖励月汇总列表(分业务)
     * @param userNo
     * @param businessCode
     * @return
     */
	List<RewardMonthResultBean> rewardSummaryMonth(@Param("userNo")String userNo, @Param("businessCode")String businessCode);
	/**
     * 业务奖励月汇总列表(不分业务)
     * @param userNo
     * @param businessCode
     * @return
     */
	List<RewardMonthResultBean> rewardSummaryMonth2(@Param("userNo")String userNo);
	
	
	/**
	 * 业务奖励日汇总列表(分业务)
	 * @param userNo
	 * @param startDate
	 * @param endDate
	 * @param businessCode
	 * @return
	 */
	List<RewardDayResultBean> rewardSummaryDay(@Param("userNo")String userNo, @Param("startDate") String startDate,
			@Param("endDate") String endDate,@Param("businessCode") String businessCode);

	/**
	 * 业务奖励日汇总列表(不分业务)
	 * @param userNo
	 * @param startDate
	 * @param endDate
	 * @param businessCode
	 * @return
	 */
	List<RewardDayResultBean> rewardSummaryDay2(@Param("userNo")String userNo, @Param("startDate")String startDate,@Param("endDate") String endDate);

	
}