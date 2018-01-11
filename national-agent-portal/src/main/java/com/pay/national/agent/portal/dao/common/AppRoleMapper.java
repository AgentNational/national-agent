package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppRole;

public interface AppRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    AppRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppRole record);

    int updateByPrimaryKey(AppRole record);

    /**
     * @Description 分页查询所有的角色信息
     * @param page
     * @param queryParams
     * @return
     * @see 需要参考的类或方法
     */
	List<AppRole> findAllAppRole(@Param("page")Page<AppRole> page, @Param("queryParams")Map<String, String> queryParams);
}