package com.pay.national.agent.core.dao.common;

import java.util.List;

import com.pay.national.agent.model.beans.results.AppMenuResultBean;
import com.pay.national.agent.model.entity.AppMenu;
import com.pay.national.agent.model.entity.AppRole;

public interface AppMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppMenu record);

    int insertSelective(AppMenu record);

    AppMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppMenu record);

    int updateByPrimaryKey(AppMenu record);

    /**
     * @Description 通过角色查找所有的菜单
     * @param list
     * @return
     * @see 需要参考的类或方法
     */
	List<AppMenu> findMenuByRole(List<AppRole> list);
}