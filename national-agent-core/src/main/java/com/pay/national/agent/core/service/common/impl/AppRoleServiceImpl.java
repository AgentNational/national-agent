package com.pay.national.agent.core.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.national.agent.core.dao.common.AppRoleMapper;
import com.pay.national.agent.core.service.common.AppRoleService;
import com.pay.national.agent.model.entity.AppRole;

@Service("appRoleService")
public class AppRoleServiceImpl implements AppRoleService{

	@Autowired
	private AppRoleMapper appRoleMapper;
	
	/**
	 * 根据角色类型查询角色信息
	 */
	@Override
	public List<AppRole> findRolesByRoleType(com.pay.national.agent.model.enums.AppRole tourists) {
		return appRoleMapper.findRolesByRoleType(tourists.name());
	}

}
