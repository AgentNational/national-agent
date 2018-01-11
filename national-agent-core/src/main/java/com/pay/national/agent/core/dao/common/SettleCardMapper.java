package com.pay.national.agent.core.dao.common;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.SettleCard;

public interface SettleCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SettleCard record);

    int insertSelective(SettleCard record);

    SettleCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettleCard record);

    int updateByPrimaryKey(SettleCard record);

	SettleCard findByownerId(@Param("userNo")String userNo);
}