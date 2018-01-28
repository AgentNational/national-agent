package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.core.bean.result.MonthRewardGatherBean;
import com.pay.national.agent.model.entity.RewardGatherMonth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RewardGatherMonthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RewardGatherMonth record);

    int insertSelective(RewardGatherMonth record);

    RewardGatherMonth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RewardGatherMonth record);

    int updateByPrimaryKey(RewardGatherMonth record);

    List<MonthRewardGatherBean> group1(@Param("userNo") String userNo, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<MonthRewardGatherBean> group2(@Param("userNo") String userNo, @Param("parentBusinessCode") String parentBusinessCode, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    RewardGatherMonth selectByMonth(@Param("month") Date month, @Param("businessCode")String businessCode,@Param("userNo") String userNo);
}