package com.pay.national.agent.core.service.wx.impl;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.core.dao.wx.WxUserInfoMapper;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/13
 */
@Service
public class WxUserInfoServiceImpl implements WxUserInfoService {
    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;

    /**
     * 根据用户的openId获取微信用户信息
     * @param openId
     * @return
     */
    @Override
    public WxUserInfo find4Login(String openId) {
        WxUserInfo wxUserInfo = wxUserInfoMapper.selectByOpenId(openId);
        if(wxUserInfo == null){
            throw new NationalAgentException(RetCodeConstants.ERROR,RetCodeConstants.ERROR_NO_USER);
        }
        return wxUserInfo;
    }

    /**
     * 插入
     * @param wxUserInfo
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insert(WxUserInfo wxUserInfo) {
        wxUserInfoMapper.insert(wxUserInfo);
    }

    /**
     * 更新
     * @param wxUserInfo
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(WxUserInfo wxUserInfo) {
        wxUserInfoMapper.updateByPrimaryKey(wxUserInfo);
    }

    @Override
    public WxUserInfo selectUserInfoByOpenId(String openId) {
        return wxUserInfoMapper.selectByOpenId(openId);
    }


}
