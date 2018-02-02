package com.pay.national.agent.core.service.wx.impl;

import com.pay.national.agent.common.bean.wx.WxJssdkConfig;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.core.service.wx.gate.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class WxJssdkConfigMethod {

    private static Logger logger = LoggerFactory.getLogger(WxJssdkConfigMethod.class);

    @Resource
    private WxService wxService;

    public WxJssdkConfig getWxJssdkConfig(String url) {
        logger.info("wxJssdkConfigService getWxJssdkConfig currentUrl:{}", url);
        String apiTicket = wxService.getEffectApiTicket();
        Map<String, String> wxSign = WxJssdkConfig.sign(apiTicket, url);
        if (wxSign != null && !wxSign.isEmpty()) {
            WxJssdkConfig wxJssdkConfig = new WxJssdkConfig();
            wxJssdkConfig.setNonceStr(wxSign.get("nonceStr"));
            wxJssdkConfig.setTimestamp(wxSign.get("timestamp"));
            wxJssdkConfig.setSignature(wxSign.get("signature"));
            wxJssdkConfig.setUrl(wxSign.get("url"));
            wxJssdkConfig.setAppId(WeiXinConstant.APP_ID);
            wxJssdkConfig.setJsapi_ticket(apiTicket);
            return wxJssdkConfig;
        } else {
            return null;
        }
    }
}
