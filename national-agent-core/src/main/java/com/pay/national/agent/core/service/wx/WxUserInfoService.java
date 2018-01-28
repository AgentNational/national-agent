package com.pay.national.agent.core.service.wx;

import com.pay.national.agent.model.entity.WxUserInfo;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/13
 */
public interface WxUserInfoService {

    /**
     * 根据用户的openId获取微信用户信息
     * @param openId
     * @return
     */
    WxUserInfo selectByOpenId(String openId);

    /**
     * 插入
     * @param wxUserInfo
     */
    void insert(WxUserInfo wxUserInfo);

    /**
     * 更新
     * @param wxUserInfo
     */
    void update(WxUserInfo wxUserInfo);
}
