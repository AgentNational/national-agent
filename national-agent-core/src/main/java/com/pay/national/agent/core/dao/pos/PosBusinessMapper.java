package com.pay.national.agent.core.dao.pos;

import java.util.List;

import com.pay.national.agent.model.entity.PosBusiness;

public interface PosBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PosBusiness record);

    int insertSelective(PosBusiness record);

    PosBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PosBusiness record);

    int updateByPrimaryKey(PosBusiness record);

	List<PosBusiness> findPosBusiness();
}