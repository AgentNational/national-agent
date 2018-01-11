package com.pay.national.agent.portal.dao.common;

import com.pay.national.agent.model.entity.PushUser;

public interface PushUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushUser record);

    int insertSelective(PushUser record);

    PushUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushUser record);

    int updateByPrimaryKey(PushUser record);

    
	PushUser findByUser(String userNo);
}