package com.pay.national.agent.core.service.wx;

import com.pay.national.agent.model.entity.WxPayBill;

public interface WxPayBillService {

    /**
     * 插入付款单
     * @param wxPayBill
     */
    void insert(WxPayBill wxPayBill);

    /**
     * 通过订单号查询付款单是否存在
     * @param orderNo
     * @return
     */
    WxPayBill findPayBillByPartnerTradeNo(String orderNo);

    /**
     * 更新付款单
     * @param wxPayBill
     */
    void update(WxPayBill wxPayBill);
}
