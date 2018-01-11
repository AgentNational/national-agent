package com.pay.national.agent.portal.dao.common;

import com.pay.national.agent.model.entity.PushMessage;

public interface PushMessageMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(PushMessage record);
    PushMessage selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(PushMessage record);
}