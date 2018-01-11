package com.pay.national.agent.core.facade.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppMessageInfoFacade;
import com.pay.national.agent.core.service.common.AppMessageInfoService;
import com.pay.national.agent.core.service.common.AppNoticeInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.MessageListQueryBean;
import com.pay.national.agent.model.beans.results.AppMessageDetailBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.beans.results.AppPopListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppMessageInfo;
import com.pay.national.agent.model.entity.AppNoticeInfo;

@Component("appMessageInfoFacade")
public class AppMessageInfoFacadeImpl implements AppMessageInfoFacade{
	private static final String NOMATCHDATA = "无匹配记录";
	private static final String QUERY_LIST_ERROR = "查询个人消息列表异常";
	private static final String QUERY_DETAIL_ERROR = "查询个人消息详情异常";
	private static final String QUERY_POP_ERROR = "查询弹屏列表异常";
	private static final String QUERY_UNREAD_COUNT_ERROR = "查询未读消息和未读公告数量异常";
	
	@Resource
	private AppMessageInfoService appMessageInfoService;
	
	@Resource
	private AppNoticeInfoService appNoticeInfoService;
	
	/**
	 * 查找当前用户所有的消息记录
	 */
	@Override
	public ReturnBean<Page<List<AppMessageListBean>>> findAllAppMessageInfo(String userNo,Integer currentPage) {
		ReturnBean<Page<List<AppMessageListBean>>> returnBean = new ReturnBean<Page<List<AppMessageListBean>>>();
		Page<List<AppMessageListBean>> page = null;
		try {
			page = new Page<List<AppMessageListBean>>();
			page.setCurrentPage(currentPage);
			MessageListQueryBean queryBean = new MessageListQueryBean();
			queryBean.setUserName(userNo);
			page = appMessageInfoService.findAllAppMessageInfo(queryBean,page);
			returnBean.setData(page);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("findAllAppMessageInfo error userNo:{},currentPage:{},exception:{}",userNo,currentPage,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300401");
			returnBean.setMsg(QUERY_LIST_ERROR);
		}
		return returnBean;
	}
	
	/**
	 * @Description 查询消息详情
	 * @param messageId
	 * @return
	 * @see 需要参考的类或方法
	 */
	@Override
	public ReturnBean<AppMessageDetailBean> findAppMessageInfoDetail(String messageId){
		ReturnBean<AppMessageDetailBean> returnBean = new ReturnBean<AppMessageDetailBean>();
		try {
			AppMessageInfo appMessageInfo = appMessageInfoService.findAppMessageInfoDetail(messageId);
			if(null == appMessageInfo){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg(NOMATCHDATA);
				return returnBean;
			}
			
			AppMessageDetailBean detailBean = new AppMessageDetailBean();
			detailBean.setId(appMessageInfo.getId());
			detailBean.setContent(appMessageInfo.getContent());
			detailBean.setTitle(appMessageInfo.getTitle());
			detailBean.setUserName(appMessageInfo.getUserName());
			detailBean.setCreateTime(appMessageInfo.getCreateTime());
			
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			returnBean.setData(detailBean);
		} catch (Exception e) {
			LogUtil.error("findAppMessageInfoDetail error messageId:{},exception:{}",messageId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300501");
			returnBean.setMsg(QUERY_DETAIL_ERROR);
		}
		return returnBean;
	}
	
	/**
	 * 查询弹窗消息和弹窗公告列表
	 * @param params
	 * @return
	 */
	@Override
	public ReturnBean<List<AppPopListBean>> findPopList(String userNo,String userRole){
		ReturnBean<List<AppPopListBean>> returnBean = new ReturnBean<List<AppPopListBean>>();
		try {
			List<AppMessageInfo> popMessageList = appMessageInfoService.findPopMessages(userNo);
			List<AppNoticeInfo>	popNoticeList =  appNoticeInfoService.findPopNotices(userNo,userRole);
			List<AppPopListBean> mergeList = mergePopList(popMessageList,popNoticeList);
			returnBean.setData(mergeList);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("findPopList error userNo:{},userRole:{},exception:{}",userNo,userRole,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300601");
			returnBean.setMsg(QUERY_POP_ERROR);
		}
		return returnBean;
	}

	/**
	 * 整合弹窗消息列表和弹窗公告列表
	 * @param popMessageList
	 * @param popNoticeList
	 * @return
	 */
	private List<AppPopListBean> mergePopList(List<AppMessageInfo> popMessageList, List<AppNoticeInfo> popNoticeList) {
		List<AppPopListBean> mergeList = new ArrayList<AppPopListBean>();
		
		if(popMessageList != null && popMessageList.size() >0){
			for (AppMessageInfo appMessageInfo : popMessageList) {
				AppPopListBean popBean = new AppPopListBean();
				popBean.setId(appMessageInfo.getId());
				popBean.setType("MESSAGE");
				popBean.setTitle(appMessageInfo.getTitle());
				popBean.setContent(appMessageInfo.getContent());
				popBean.setCreateTime(appMessageInfo.getCreateTime());
				mergeList.add(popBean);
			}
		}
		
		if(popNoticeList != null && popNoticeList.size() > 0){
			for (AppNoticeInfo appNoticeInfo : popNoticeList) {
				AppPopListBean popBean = new AppPopListBean();
				popBean.setId(appNoticeInfo.getId());
				popBean.setType("NOTICE");
				popBean.setTitle(appNoticeInfo.getTitle());
				popBean.setContent(appNoticeInfo.getContent());
				popBean.setCreateTime(appNoticeInfo.getCreateTime());
				mergeList.add(popBean);
			}
		}
		return sortPopList(mergeList);
	}

	/**
	 * 弹窗列表按创建时间倒序
	 * @param mergeList
	 */
	private List<AppPopListBean> sortPopList(List<AppPopListBean> mergeList) {
		List<AppPopListBean> result = new  ArrayList<AppPopListBean>();
		if(mergeList != null && mergeList.size() > 1){
			try{
				 Object[] objs = mergeList.toArray();
				Arrays.sort(objs, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						AppPopListBean  m1 = (AppPopListBean)o1;
						AppPopListBean  m2 = (AppPopListBean)o2;
						
						Date d1 = (m1.getCreateTime() == null ? new Date() :m1.getCreateTime());
						Date d2 = (m2.getCreateTime() == null ? new Date() :m2.getCreateTime());
						
						int i = d1.compareTo(d2);
						return 0 - i;
					}
				});
				
				List<Object> l = Arrays.asList(objs);
				for(Object o : l){
					if(o instanceof AppPopListBean ){
						AppPopListBean m = (AppPopListBean)o;
						result.add(m);
					}
				}
			}catch(Exception e){
				LogUtil.error("sort 弹窗列表按创建时间倒序异常 :"+e);
				result = mergeList;
			}
		}else{
			result = mergeList;
		}
		return result;
	}
	
	
	/**
	 * 查询用户未读消息数和未读公告数
	 * @param params
	 * @return
	 */
	@Override
	public ReturnBean<Map<String,Integer>> findUnReadCount(String userNo,String userRole) {
		ReturnBean<Map<String,Integer>> returnBean  = new ReturnBean<Map<String,Integer>>();
		try {
			Integer unReadMessage = appMessageInfoService.findUnReadCount(userNo);
			Integer unReadNotice = appNoticeInfoService.findUnReadCount(userNo,userRole);
			
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("unReadMessage", unReadMessage);
			map.put("unReadNotice", unReadNotice);
			
			returnBean.setData(map);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("findUnReadCount error  userNo:{},userRole:{},exception:{{}",userNo,userRole,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0300701");
			returnBean.setMsg(QUERY_UNREAD_COUNT_ERROR);
		}
		return returnBean;
	}

	/**
	 * 修改消息读取状态
	 */
	@Override
	public ReturnBean<Object> updateIsRead(String usreNo,String[] ids) {
		return appMessageInfoService.updateIsRead(usreNo,ids);
	}
	
	/**
	 * 删除个人消息
	 */
	@Override
	public ReturnBean<Object> deleteMessage(String messageId) {
		return appMessageInfoService.deleteMessage(messageId);
	}

}
