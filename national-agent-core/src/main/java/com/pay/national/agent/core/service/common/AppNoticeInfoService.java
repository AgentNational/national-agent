package com.pay.national.agent.core.service.common;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;
import com.pay.national.agent.model.entity.AppNoticeInfo;

public interface AppNoticeInfoService {
	/**
	 * 记录用户已读取公告
	 * @param userNo 用户编号
	 * @param idArr 消息id数组
	 * @return
	 */
	ReturnBean<Object> readNotice(String userNo,String[] idArr);

	/**
	 * 删除公告
	 * @param userNo
	 * @param noticeId
	 * @return
	 */
	ReturnBean<Object> deleteNotice(String userNo, String noticeId);
	/**
	 * 公告列表
	 * @param queryBean
	 * @param page
	 * @return
	 */
	Page<List<AppNoticeListBean>> findAllNotice(NoticeListQueryBean queryBean, Page<List<AppNoticeListBean>> page);
	/**
	 * 未读公告条数
	 * @param userNo
	 * @param userGroup
	 * @return
	 */
	Integer findUnReadCount(String userNo,String userGroup);
	
	/**
	 * 公告详情
	 * @param noticeId
	 * @return
	 */
	ReturnBean<AppNoticeDetailBean> noticeDetail(String noticeId);
	
	/**
	 * 查询用户弹窗公告
	 * @param userNo
	 * @param userGroup
	 * @return
	 */
	List<AppNoticeInfo> findPopNotices(String userNo,String userGroup);
	
	/**
	 * 创建公告
	 * @param notice
	 */
	void createNotice(AppNoticeInfo notice);

}
