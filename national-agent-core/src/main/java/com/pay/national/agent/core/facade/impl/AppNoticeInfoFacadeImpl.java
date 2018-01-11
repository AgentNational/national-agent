package com.pay.national.agent.core.facade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppNoticeInfoFacade;
import com.pay.national.agent.core.service.common.AppNoticeInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

@Component("appNoticeInfoFacade")
public class AppNoticeInfoFacadeImpl implements AppNoticeInfoFacade{
	private static final String QUERY_LIST_ERROR = "查询公告列表异常";
	@Resource
	private AppNoticeInfoService appNoticeInfoService;
	
	/**
	 * 记录用户已读取公告
	 * @param userNo 用户编号
	 * @param ids
	 * @return
	 */
	@Override
	public ReturnBean<Object> readNotice(String userNo,String[] ids) {
		return appNoticeInfoService.readNotice(userNo,ids);
	}
	
	
	/**
	 * 删除公告
	 * @param userNo
	 * @param noticeId
	 * @return
	 */
	@Override
	public ReturnBean<Object> deleteNotice(String userNo,String noticeId) {
		return appNoticeInfoService.deleteNotice(userNo,noticeId);
	}
	
	/**
	 * 公告列表
	 * @param queryBean
	 * @param page
	 * @return
	 */
	@Override
	public ReturnBean<Page<List<AppNoticeListBean>>> findAllNotice(NoticeListQueryBean queryBean,Page<List<AppNoticeListBean>> page) {
		ReturnBean<Page<List<AppNoticeListBean>>> returnListBean = new ReturnBean<Page<List<AppNoticeListBean>>>();
		try {
			page = appNoticeInfoService.findAllNotice(queryBean,page);
			returnListBean.setData(page);
			returnListBean.setCode(RetCodeConstants.SUCCESS);
			returnListBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("findAllNotice error queryBean:{},page:{}",queryBean,page,e);
			returnListBean.setCode(RetCodeConstants.ERROR);
			returnListBean.setErrorCode("0300901");
			returnListBean.setMsg(QUERY_LIST_ERROR);
		}
		return returnListBean;
	}
	
	/**
	 * 公告详情
	 * @param noticeId
	 * @return
	 */
	@Override
	public ReturnBean<AppNoticeDetailBean> noticeDetail(String noticeId) {
		return appNoticeInfoService.noticeDetail(noticeId);
	}
}
