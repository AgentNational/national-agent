package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;
import com.pay.national.agent.model.entity.AppNoticeInfo;

public interface AppNoticeInfoMapper {
	List<AppNoticeListBean> findAllNotice(@Param("queryBean")NoticeListQueryBean queryBean,
			@Param("page") Page<List<AppNoticeListBean>> page);

	List<String> findIds(@Param("userGroup")String userGroup);

	AppNoticeDetailBean getNoticeDetail(@Param("noticeId")String noticeId);

	List<AppNoticeInfo> findPopNotice(@Param("userGroup")String userGroup);

	void insert(AppNoticeInfo notice);
}