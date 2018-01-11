package com.pay.national.agent.portal.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppRole;
import com.pay.national.agent.portal.dao.common.AppRoleMapper;
import com.pay.national.agent.portal.service.common.AppRoleService;

@Service("appRoleService")
public class AppRoleServiceImpl implements AppRoleService{

	@Resource
	private AppRoleMapper appRoleMapper;
	/**
	 * 分页查询所有的角色信息
	 */
	@Override
	public List<AppRole> findAllAppRole(Page<AppRole> page, Map<String, String> queryParams) {
		return appRoleMapper.findAllAppRole(page,queryParams);
	}
	/**
	 * 通过ID查找角色
	 */
	@Override
	public AppRole findAppRoleByRoleId(String roleId) {
		return appRoleMapper.selectByPrimaryKey(Integer.parseInt(roleId));
	}
	/**
	 * 修改角色
	 */
	@Override
	public void modifyAppRole(AppRole appRole) {
		appRoleMapper.updateByPrimaryKeySelective(appRole);
	}
	/**
	 * 添加角色
	 */
	@Override
	public void addAppRole(AppRole appRole) {
		appRoleMapper.insert(appRole);
	}

}
