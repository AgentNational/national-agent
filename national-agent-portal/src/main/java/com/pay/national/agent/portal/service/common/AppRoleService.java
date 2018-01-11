package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppRole;

public interface AppRoleService {

	/**
	 * @Description 分页查询所有的角色信息
	 * @param page
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<AppRole> findAllAppRole(Page<AppRole> page, Map<String, String> queryParams);

	/**
	 * @Description 通过id查找角色
	 * @param roleId
	 * @return
	 * @see 需要参考的类或方法
	 */
	AppRole findAppRoleByRoleId(String roleId);

	/**
	 * @Description 修改app角色
	 * @param appRole
	 * @see 需要参考的类或方法
	 */
	void modifyAppRole(AppRole appRole);

	/**
	 * @Description 添加角色
	 * @param appRole
	 * @see 需要参考的类或方法
	 */
	void addAppRole(AppRole appRole);

}
