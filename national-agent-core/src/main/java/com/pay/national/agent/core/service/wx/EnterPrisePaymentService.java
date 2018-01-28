package com.pay.national.agent.core.service.wx;

import com.pay.national.agent.model.beans.ReturnBean;

import java.util.Map;

/**
 * 企业付款
 */
public interface EnterPrisePaymentService {

    /**
     * 创建企业付款订单
     * param.put("openId",openId) //用户openId，通过网页授权获得。
     * param.put("amount",amount) //付款金额
     * param.get("desc") //描述
     * param.get("ip") //调用方ip地址
     * param.get("notifyURL")//回调url
     * @return
     */
    ReturnBean<Object> createPayBill(Map<String,String> params);

    /**
     * 企业付款  实际划款
     * @param orderNo 企业付款订单编号
     * @return
     */
    String payment(String orderNo);
}
