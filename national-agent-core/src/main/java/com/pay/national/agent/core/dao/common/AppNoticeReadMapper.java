package com.pay.national.agent.core.dao.common;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.AppNoticeRead;

public interface AppNoticeReadMapper {
	int deleteByPrimaryKey(String id);

    int insert(AppNoticeRead record);

    AppNoticeRead selectByPrimaryKey(String id);

    int updateByPrimaryKey(AppNoticeRead record);

	AppNoticeRead getByNotice(@Param("userName")String userName,@Param("noticeId") String noticeId);
}