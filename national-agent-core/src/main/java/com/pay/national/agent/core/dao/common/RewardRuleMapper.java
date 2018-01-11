package com.pay.national.agent.core.dao.common;

import java.util.List;

import com.pay.national.agent.model.entity.RewardRule;

public interface RewardRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RewardRule record);

    int insertSelective(RewardRule record);

    RewardRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RewardRule record);

    int updateByPrimaryKey(RewardRule record);

    /**
     * 
     * @param childBusinessCode
     * @return
     */
	List<RewardRule> getRewardRules(String childBusinessCode);
}