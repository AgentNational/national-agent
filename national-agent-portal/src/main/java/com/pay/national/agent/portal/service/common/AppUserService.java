package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppMenu;
import com.pay.national.agent.model.entity.AppUser;

public interface AppUserService {

	/**
	 * @Description 分页查询所有用户
	 * @param page
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<AppUser> findAllAppUser(Page<AppUser> page, Map<String, Object> queryParams);

	/**
	 * @Description 通过id查询用户信息
	 * @param userId
	 * @return
	 * @see 需要参考的类或方法
	 */
	AppUser findAppUserByUserId(String userId);

	/**
	 * @Description 修改用户信息
	 * @param appUser
	 * @see 需要参考的类或方法
	 */
	void modifyAppUser(AppUser appUser);

	/**
	 * @Description 添加app菜单
	 * @param appUser
	 * @see 需要参考的类或方法
	 */
	void addAppMenu(AppUser appUser);

	List<Map<String, Object>> findRole();

	String[] findAuthorizeUser(String userId);


	void updateUserStatus(Long userId, String status);

	/**
	 * 
	 * @Description 一句话描述方法的用法
	 * @param roleId
	 * @param userId
	 * @see 需要参考的类或方法
	 */
	void saveUserAuthorize(String roleId, String userId);

	/**
	 * @Description 一句话描述方法的用法
	 * @param appRole
	 * @param userId
	 * @see 需要参考的类或方法
	 */
	void deleteUserAuthorize(String appRole, String userId);


}
