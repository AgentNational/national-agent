package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.AppRole;

public interface AppRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    AppRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppRole record);

    int updateByPrimaryKey(AppRole record);

    /**
     * @Description 通过角色类型查找角色信息
     * @param name
     * @return
     * @see 需要参考的类或方法
     */
	List<AppRole> findRolesByRoleType(@Param("roleType")String roleType);
}