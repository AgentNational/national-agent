package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.RewardGather;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RewardGatherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RewardGather record);

    int insertSelective(RewardGather record);

    RewardGather selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RewardGather record);

    int updateByPrimaryKey(RewardGather record);

    List<Map<String,Object>> selectByUser(@Param("userNo") String userNo);

    RewardGather selectByBusiness(@Param("userNo") String userNo, @Param("businessCode") String businessCode);
}