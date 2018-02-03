package com.pay.national.agent.core.service.wx;

import com.pay.national.agent.model.entity.WxUserInfo;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/13
 */
public interface WxUserInfoService {

    /**
     *
     * @param openId
     * @return
     */
    WxUserInfo find4Login(String openId);

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
