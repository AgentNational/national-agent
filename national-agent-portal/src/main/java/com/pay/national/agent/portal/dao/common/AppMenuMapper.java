package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppMenu;

public interface AppMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppMenu record);

    int insertSelective(AppMenu record);

    AppMenu selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(AppMenu record);

    int updateByPrimaryKey(AppMenu record);

	List<AppMenu> findAllAppMenu(@Param("page")Page<AppMenu> page, @Param("queryParams")Map<String, Object> queryParams);

	List<Map<String, Object>> findRole();

	String[] findAuthorizeRole(@Param("menuId")String menuId);

	Object checkAuthorize(@Param("roleId")String roleId, @Param("menuId")String menuId);

	void saveMenuAuthorize(@Param("roleId")String roleId, @Param("menuId")String menuId);

	void updateAuthorizeStatus(@Param("roleId")String roleId, @Param("menuId")String menuId, @Param("status")String status);

}