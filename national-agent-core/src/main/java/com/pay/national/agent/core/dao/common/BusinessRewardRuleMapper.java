package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.BusinessRewardRule;
import org.apache.ibatis.annotations.Param;

public interface BusinessRewardRuleMapper{
    int deleteByPrimaryKey(Long id);

    int insert(BusinessRewardRule record);

    int insertSelective(BusinessRewardRule record);

    BusinessRewardRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessRewardRule record);

    int updateByPrimaryKey(BusinessRewardRule record);

    BusinessRewardRule selectByBusiness(@Param("businessCode") String businessCode);
}