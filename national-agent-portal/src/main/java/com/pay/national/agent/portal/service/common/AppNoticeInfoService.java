package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppNoticeInfo;

public interface AppNoticeInfoService {
	/**
	 * @Description 分页查找所以的公告;
	 * @param page
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, Object>> findAllAppNotice(Page<Map<String, Object>> page, Map<String, Object> queryParams);

	/**
	 * @Description 添加app公告
	 * @param appNoticeInfo
	 * @see 需要参考的类或方法
	 */
	void addAppNotice(AppNoticeInfo appNoticeInfo);

	/**
	 * @Description 通过公告Id查找公告
	 * @param noticeId
	 * @return
	 * @see 需要参考的类或方法
	 */
	AppNoticeInfo find(String noticeId);

	/**
	 * @Description 修改appNoticeInfo
	 * @param appNoticeInfo
	 * @see 需要参考的类或方法
	 */
	void update(AppNoticeInfo appNoticeInfo);

	/**
	 * 删除公告
	 * @param noticeId
	 */
	void deleteNotice(String noticeId);

}
