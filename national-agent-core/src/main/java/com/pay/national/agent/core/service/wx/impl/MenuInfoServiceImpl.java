package com.pay.national.agent.core.service.wx.impl;

import com.pay.national.agent.core.dao.wx.MenuInfoMapper;
import com.pay.national.agent.core.service.wx.MenuInfoService;
import com.pay.national.agent.model.entity.MenuInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuInfoService")
public class MenuInfoServiceImpl implements MenuInfoService{

    @Resource
    private MenuInfoMapper menuInfoMapper;

    @Override
    public MenuInfo findMenuInfoByMenuNo(String menuNo) {
        return menuInfoMapper.findMenuInfoByMenuNo(menuNo);
    }

    @Override
    public List<MenuInfo> findAllFatherMenu() {
        return menuInfoMapper.findFatherMenu();
    }

    @Override
    public List<MenuInfo> findChildMenuInfo(Long id) {
        return menuInfoMapper.findChildMenuInfo(id);
    }
}
