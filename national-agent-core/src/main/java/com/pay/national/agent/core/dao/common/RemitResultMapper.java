package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.RemitResult;

public interface RemitResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RemitResult record);

    RemitResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RemitResult record);
}