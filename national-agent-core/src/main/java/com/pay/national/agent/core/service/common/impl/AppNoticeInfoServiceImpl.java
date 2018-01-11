package com.pay.national.agent.core.service.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.AppNoticeInfoMapper;
import com.pay.national.agent.core.dao.common.AppNoticeReadMapper;
import com.pay.national.agent.core.service.common.AppNoticeInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppNoticeInfo;
import com.pay.national.agent.model.entity.AppNoticeRead;
import com.pay.national.agent.model.enums.AppNoticeType;

@Service("appNoticeInfoService")
public class AppNoticeInfoServiceImpl implements AppNoticeInfoService{
	private static final String QUERY_DETAIL_ERROR = "查询公告详情异常";
	private static final String SET_READ_ERROR = "记录用户公告已读异常";
	private static final String SET_READ_LOCK = "setReadLock";
	
	@Resource
	private AppNoticeInfoMapper appNoticeInfoMapper;
	
	@Resource
	private AppNoticeReadMapper appNoticeReadMapper;
	
	/**
	 * 记录用户已读取公告
	 * @param userNo
	 * @param idArray
	 * @return
	 */
	@Override
	public ReturnBean<Object> readNotice(String userNo,String[] idArray) {
		ReturnBean<Object> returnBean = new ReturnBean<Object>();
		try {
			for (int i = 0; i < idArray.length; i++) {
				AppNoticeRead read = appNoticeReadMapper.getByNotice(userNo,idArray[i]);
				if(null == read){
					AppNoticeRead appNoticeRead = new AppNoticeRead();
					appNoticeRead.setId(CommonCodeUtil.getPkId());
					appNoticeRead.setOptimistic(0);
					appNoticeRead.setNoticeId(idArray[i]);
					appNoticeRead.setReadTime(new Date());
					appNoticeRead.setUserName(userNo);
					appNoticeRead.setStatus("READ");
					readNotice(appNoticeRead);
				}
			}
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("readNotice error userNo:{},idArray:{},exception:{}",userNo,idArray,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0301001");
			returnBean.setMsg(SET_READ_ERROR);
		}
		return returnBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void readNotice(AppNoticeRead appNoticeRead) {
		Long lock = CacheUtils.setnx(SET_READ_LOCK+appNoticeRead.getUserName()+appNoticeRead.getId(), "noticeReadLock");
		if(lock != 0l){
			CacheUtils.expire(SET_READ_LOCK+appNoticeRead.getUserName()+appNoticeRead.getId(), 5);
			appNoticeReadMapper.insert(appNoticeRead);
		}
	}
	
	/**
	 * 删除公告
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> deleteNotice(String userNo, String noticeId) {
		ReturnBean<Object> returnBean = new ReturnBean<Object>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			AppNoticeRead appNoticeRead = appNoticeReadMapper.getByNotice(userNo, noticeId);
			if(null == appNoticeRead){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("请阅读该公告后再选择删除");
				return returnBean;
			}
			appNoticeRead.setStatus("DELETE");
			appNoticeReadMapper.updateByPrimaryKey(appNoticeRead);
		} catch (Exception e) {
			LogUtil.error("deleteNotice error userNo:{},noticeId:{},exception:{}",userNo,noticeId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0301201");
			returnBean.setMsg("删除公告异常");
		}
		return returnBean;
	}

	@Override
	public Page<List<AppNoticeListBean>> findAllNotice(NoticeListQueryBean queryBean,
		Page<List<AppNoticeListBean>> page) {
		List<AppNoticeListBean> noticeList = appNoticeInfoMapper.findAllNotice(queryBean,page);
		List<AppNoticeListBean> newNoticeList = new ArrayList<>();
		
		if(null != noticeList && noticeList.size() >0){
			for (AppNoticeListBean appNoticeListBean : noticeList) {
				try {
					AppNoticeRead read =  appNoticeReadMapper.getByNotice(queryBean.getUserName(), appNoticeListBean.getId());
					if(null != read){
						if(!"DELETE".equals(read.getStatus())){
							appNoticeListBean.setIsRead("Y");
							newNoticeList.add(appNoticeListBean);
						}
					}else{
						appNoticeListBean.setIsRead("N");
						newNoticeList.add(appNoticeListBean);
					}
				} catch (Exception e) {
					LogUtil.error("findAllNotice 设置notice 已读、未读状态异常 error:{}",e);
				}
			}
		}
		
		page.setObject(newNoticeList);
		return page;
	}
	
	/**
	 * 未读公告条数
	 * @param params
	 * @return
	 */
	@Override
	public Integer findUnReadCount(String userNo,String userGroup) {
		LogUtil.info("Method findUnReadCount userNo:{},userGroup:{}",userNo,userGroup);
		int count = 0;
		try {
			List<String> noticeList = appNoticeInfoMapper.findIds(userGroup);
			if(null != noticeList && noticeList.size() > 0){
				for (String noticeId : noticeList) {
					AppNoticeRead noticeRead = appNoticeReadMapper.getByNotice(userNo, noticeId);
					if(null == noticeRead){
						count ++;
					}
				}
			}
		} catch (Exception e) {
			LogUtil.error("Method findUnReadCount error ",e);
		}
		return count;
	}
	
	/**
	 * 公告详情
	 * @param params
	 * @return
	 */
	@Override
	public ReturnBean<AppNoticeDetailBean> noticeDetail(String noticeId) {
		ReturnBean<AppNoticeDetailBean> returnBean = new ReturnBean<AppNoticeDetailBean>();
		try {
			AppNoticeDetailBean noticedetail =  appNoticeInfoMapper.getNoticeDetail(noticeId);
			returnBean.setData(noticedetail);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("Method noticeDetail error noticeId:{},exception:{}",noticeId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0301101");
			returnBean.setMsg(QUERY_DETAIL_ERROR);
		}
		return returnBean;
	}
	
	
	/**
	 * 查询用户弹窗公告
	 * @param userNo
	 * @return
	 */
	@Override
	public List<AppNoticeInfo> findPopNotices(String userNo,String userGroup) {
		LogUtil.info("Method  findPopNotices userNo:{},userGroup:{}",userNo,userGroup);
		List<AppNoticeInfo> needPopList = new ArrayList<AppNoticeInfo>();
		List<AppNoticeInfo> allPopList = appNoticeInfoMapper.findPopNotice(userGroup);
		if(null != allPopList && allPopList.size()>0){
			for (AppNoticeInfo appNoticeInfo : allPopList) {
				AppNoticeRead appNoticeRead = appNoticeReadMapper.getByNotice(userNo, appNoticeInfo.getId());
				if(null == appNoticeRead){
					needPopList.add(appNoticeInfo);
				}
			}
		}
		return needPopList;
	}

	@Override
	public void createNotice(AppNoticeInfo notice) {
		if(null != notice){
			notice = validateNotice(notice);
			appNoticeInfoMapper.insert(notice);
		}
	}
	
	private AppNoticeInfo validateNotice(AppNoticeInfo appNoticeInfo) {
		if(StringUtils.isBlank(appNoticeInfo.getId())){
			appNoticeInfo.setId(CommonCodeUtil.getPkId());
		}
		if(StringUtils.isBlank(appNoticeInfo.getUserGroup())){
			appNoticeInfo.setUserGroup("1");//普通代理人
		}
		if(StringUtils.isBlank(appNoticeInfo.getNoticeType())){
			appNoticeInfo.setNoticeType(AppNoticeType.COMMON.name());//普通公告
		}
		if(StringUtils.isBlank(appNoticeInfo.getOperator())){
			appNoticeInfo.setOperator("system");
		}
		if(StringUtils.isBlank(appNoticeInfo.getUsableStatus())){
			appNoticeInfo.setUsableStatus("DISABLE");
		}
		if(null  == appNoticeInfo.getIsPop()){
			appNoticeInfo.setIsPop(false);
		}
		if(null  == appNoticeInfo.getCreateTime()){
			appNoticeInfo.setCreateTime(new Date());
		}
		return appNoticeInfo;
	}
}
