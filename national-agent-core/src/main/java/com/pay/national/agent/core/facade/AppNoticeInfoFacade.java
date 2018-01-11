package com.pay.national.agent.core.facade;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;

public interface AppNoticeInfoFacade {
	/**
	 * 公告列表
	 * @param queryBean
	 * @param page
	 * @return
	 */
	ReturnBean<Page<List<AppNoticeListBean>>> findAllNotice(NoticeListQueryBean queryBean,Page<List<AppNoticeListBean>> page);

	/**
	 * 记录用户已读取公告
	 * @param userNo 用户编号
	 * @param ids 公告id数组
	 * @return
	 */
	ReturnBean<Object> readNotice(String userNo,String[] ids);

	/**
	 * 公告详情
	 * @param noticeId 公告Id
	 * @return
	 */
	ReturnBean<AppNoticeDetailBean> noticeDetail(String noticeId);

	/**
	 * 删除公告
	 * @param userNo 用户编号
	 * @param noticeId 公告id
	 * @return
	 */
	ReturnBean<Object> deleteNotice(String userNo,String noticeId);

	
}
