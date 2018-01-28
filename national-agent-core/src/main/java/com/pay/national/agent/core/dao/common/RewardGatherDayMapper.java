package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.core.bean.result.DayRewardGatherBean;
import com.pay.national.agent.model.entity.RewardGatherDay;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RewardGatherDayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RewardGatherDay record);

    int insertSelective(RewardGatherDay record);

    RewardGatherDay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RewardGatherDay record);

    int updateByPrimaryKey(RewardGatherDay record);

    List<DayRewardGatherBean> group1(@Param("userNo") String userNo, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<DayRewardGatherBean> group2(@Param("userNo") String userNo, @Param("parentBusinessCode") String parentBusinessCode, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    void summary(@Param("userNo") String userNo,@Param("startDate")String startDate,@Param("endDate")String endDate);

    List<RewardGatherDay> selectByRewardDay(@Param("day") Date day, @Param("userNo") String userNo);
}