package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.RemitLog;

public interface RemitLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RemitLog record);

    RemitLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RemitLog record);
}