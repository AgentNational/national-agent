package com.pay.national.agent.core.service.wx.impl;

import com.pay.national.agent.core.dao.wx.WxPayBillMapper;
import com.pay.national.agent.core.service.wx.WxPayBillService;
import com.pay.national.agent.model.entity.WxPayBill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("wxPayBillService")
public class WxPayBillServiceImpl implements WxPayBillService {

    @Resource
    private WxPayBillMapper wxPayBillMapper;

    @Override
    public void update(WxPayBill wxPayBill) {
        wxPayBillMapper.updateByPrimaryKey(wxPayBill);
    }

    @Override
    public void insert(WxPayBill wxPayBill) {
        wxPayBillMapper.insert(wxPayBill);
    }

    @Override
    public WxPayBill findPayBillByPartnerTradeNo(String orderNo) {
        return wxPayBillMapper.findPayBillByPartnerTradeNo(orderNo);
    }
}
