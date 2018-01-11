package com.pay.national.agent.core.service.common;

import java.util.List;

import com.pay.national.agent.model.entity.AppRole;

public interface AppRoleService {

	/**
	 * @Description 通过角色类型查找角色
	 * @param tourists
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<AppRole> findRolesByRoleType(com.pay.national.agent.model.enums.AppRole tourists);

}
