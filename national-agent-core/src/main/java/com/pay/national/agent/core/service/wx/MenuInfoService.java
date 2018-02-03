package com.pay.national.agent.core.service.wx;

import com.pay.national.agent.model.entity.MenuInfo;

import java.util.List;

public interface MenuInfoService {
    /**
     * 通过menuNo查询menu信息
     * @param menuNo
     * @return
     */
    MenuInfo findMenuInfoByMenuNo(String menuNo);

    /**
     * 查询所有的父级菜单
     * @return
     */
    List<MenuInfo> findAllFatherMenu();

    /**
     * 查找子菜单
     * @param id
     * @return
     */
    List<MenuInfo> findChildMenuInfo(Long id);
}
