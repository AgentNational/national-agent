package com.pay.national.agent.core.service.wx;

import java.util.Map;

public interface WxPublicPayService {


    /**
     * @Description 微信支付生成订单
     * @param params
     * @return
     * @see
     */
    public Map<String, String> createPayBill(Map<String, String> params);

    /**
     * @Description 微信支付异步通知
     * @param notifyXml
     * @return
     * @see
     */
    public Map<String, String> updateNotifyPay(String notifyXml);

    /**
     * @Description 验证微信支付是否成功
     * @param outerTradeNo
     * @return
     * @see
     */
    public Map<String, String> toCheckWxpay(String outerTradeNo);

}
