package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.RewardGather;

public interface RewardGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardGather record);

    RewardGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RewardGather record);
    
	List<RewardGather> selectByBusiness(@Param("userNo")String userNo, @Param("businessCode")String businessCode, @Param("childBusinessCode")String childBusinessCode);
}