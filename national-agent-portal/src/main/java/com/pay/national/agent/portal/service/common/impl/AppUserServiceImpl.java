package com.pay.national.agent.portal.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.portal.dao.common.AppUserMapper;
import com.pay.national.agent.portal.service.common.AppUserService;

/**
 * @Description: app用户service
 * @see: 需要参考的类
 * @version 2017年9月25日 上午11:03:03
 * @author zhenhui.liu
 */
@Service("appUserService")
public class AppUserServiceImpl implements AppUserService{

	@Resource
	private AppUserMapper appUserMapper;
	@Override
	public List<AppUser> findAllAppUser(Page<AppUser> page, Map<String, Object> queryParams) {
		return appUserMapper.findAllAppUser(page,queryParams);
		 
	}

	@Override
	public AppUser findAppUserByUserId(String userId) {
		return null;
	}

	@Override
	public void modifyAppUser(AppUser appUser) {
		appUserMapper.updateByPrimaryKeySelective(appUser);
	}

	@Override
	public void addAppMenu(AppUser appUser) {
		
	}

	@Override
	public List<Map<String, Object>> findRole() {
		return appUserMapper.findRole();
	}

	@Override
	public String[] findAuthorizeUser(String userId) {
		return appUserMapper.findAuthorizeUser(userId);
	}


	/**
	 * 修改用户状态
	 */
	@Override
	public void updateUserStatus(Long userId, String status) {
		appUserMapper.updateUserStatus(userId,status);
	}

	@Override
	public void saveUserAuthorize(String roleId, String userId) {
		appUserMapper.saveUserAuthorize(roleId,userId);
	}

	@Override
	public void deleteUserAuthorize(String appRole, String userId) {
		appUserMapper.updateAuthorizeStatus(appRole,userId,"DISABLE");
	}

}
