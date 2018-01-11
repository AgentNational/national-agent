package com.pay.national.agent.portal.service.common.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.AppNoticeInfo;
import com.pay.national.agent.model.enums.AppNoticeType;
import com.pay.national.agent.portal.dao.common.AppNoticeInfoMapper;
import com.pay.national.agent.portal.service.common.AppNoticeInfoService;

@Service("appNoticeInfoService")
public class AppNoticeInfoServiceImpl implements AppNoticeInfoService{
	@Resource
	private AppNoticeInfoMapper appNoticeInfoMapper;
	
	/**
	 * 分页查找所以的app公告信息
	 */
	@Override
	public List<Map<String, Object>> findAllAppNotice(Page<Map<String, Object>> page, Map<String, Object> queryParams) {
		List<Map<String, Object>> list = null;
		try {
			list = appNoticeInfoMapper.findAllAppNotice(page ,queryParams);
			LogUtil.info("Method findAllAppNotice list:{}",list);
		} catch (Exception e) {
			LogUtil.error("Method findAllAppNotice error:{}",e);
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addAppNotice(AppNoticeInfo appNoticeInfo) {
		if(null != appNoticeInfo){
			appNoticeInfo = validateNotice(appNoticeInfo);
			appNoticeInfoMapper.insert(appNoticeInfo);
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

	@Override
	public AppNoticeInfo find(String noticeId) {
		return appNoticeInfoMapper.selectByPrimaryKey(noticeId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(AppNoticeInfo appNoticeInfo) {
		appNoticeInfoMapper.updateByPrimaryKey(appNoticeInfo);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteNotice(String noticeId) {
		appNoticeInfoMapper.deleteReadRecord(noticeId);
		appNoticeInfoMapper.deleteNotice(noticeId);
	}

}
