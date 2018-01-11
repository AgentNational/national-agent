package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.OpenAccountFail;

public interface OpenAccountFailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OpenAccountFail record);

    int insertSelective(OpenAccountFail record);

    OpenAccountFail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpenAccountFail record);

    int updateByPrimaryKey(OpenAccountFail record);
}