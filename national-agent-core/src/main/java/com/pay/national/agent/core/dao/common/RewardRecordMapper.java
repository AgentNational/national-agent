package com.pay.national.agent.core.dao.common;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.RewardRecord;

public interface RewardRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardRecord record);

    RewardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RewardRecord record);

	RewardRecord getRewardRecord(@Param("userNo")String userNo,@Param("orderNo")String orderNo,@Param("ruleId")Integer ruleId);
}