package com.pay.national.agent.core.service.wx;

import java.io.UnsupportedEncodingException;

public interface QrCodeInfoService {
    /**
     * 获取微信
     * @param openId
     * @return
     */
    String getQrCodeUrl(String openId) throws UnsupportedEncodingException;
}
