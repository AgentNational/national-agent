package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.RewardRecord;

public interface RewardRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RewardRecord record);

    int insertSelective(RewardRecord record);

    RewardRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RewardRecord record);

    int updateByPrimaryKey(RewardRecord record);
}