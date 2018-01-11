package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.UserLoginLog;

public interface UserLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}