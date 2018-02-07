package com.pay.national.agent.core.service.wx.impl;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.bean.wx.EnterPrisePaymentBean;
import com.pay.national.agent.common.constants.PayConstants;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.wx.BeanToXmlUtil;
import com.pay.national.agent.common.utils.wx.UUIDHexGenerator;
import com.pay.national.agent.core.service.wx.EnterPrisePaymentService;
import com.pay.national.agent.core.service.wx.WxPayBillService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.WxPayBill;
import com.pay.national.agent.model.enums.SuccessFail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * 企业付款实现
 */
@Service("enterPrisePaymentService")
public class EnterPrisePaymentServiceImpl implements EnterPrisePaymentService {
    /**
     * 微信企业付款相关service
     */
    @Resource
    private WxPayBillService wxPayBillService;

    /**
     * 创建企业付款单据。记录企业付款的状态
     * @param param
     * @return
     */
    @Override
    public ReturnBean<Object> createPayBill(Map<String, String> param) {
        LogUtil.info("创建企业付款单据 param:{}", JSON.toJSON(param));
        String openId = param.get("openId");
        BigDecimal amount = new BigDecimal(param.get("amount"));
        LogUtil.info(JSON.toJSONString(param));
        try {
            WxPayBill wxPayBill = new WxPayBill();
            UUIDHexGenerator uuidGenerator = UUIDHexGenerator.getInstance();
            String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String code = uuidGenerator.createCode(10);
            String mchid = PayConstants.mchid;// 商户号
            wxPayBill.setPartnerTradeNo(mchid + today + code);// 商户订单号
            wxPayBill.setOpenid(openId);// 接收款项用户openid
            wxPayBill.setNonceStr(uuidGenerator.generate());// 获取uuid作为随机字符串
            wxPayBill.setCheckName("NO_CHECK");// 校验用户姓名选项，默认为不校验
            wxPayBill.setAmount(amount);// 打款金额
            wxPayBill.setDescription(param.get("desc"));// 企业付款描述
            wxPayBill.setIp(param.get("ip"));// 操作ip
            wxPayBill.setReturnCode(SuccessFail.FAIL.name());// 通信标示默认FAIL
            wxPayBill.setResultCode(SuccessFail.FAIL.name());// 下单业务结果默认FAIL
            wxPayBill.setRemark(param.get("notifyURL"));// 业务通知回调地址
            LogUtil.info("创建企业付款单入参 wxPayBill:{}",JSON.toJSONString(wxPayBill));
            wxPayBillService.insert(wxPayBill);
            return new ReturnBean<Object>(RetCodeConstants.SUCCESS, "创建成功", wxPayBill.getPartnerTradeNo());

        } catch (Exception e) {
            LogUtil.error("创建微信企业付款异常 exceptioninfo:{}", e.getMessage());
            return new ReturnBean<Object>(RetCodeConstants.FAIL, RetCodeConstants.FAIL_DESC);
        }

    }

    @Resource
    private WxService wxService;

    /**
     * 进行企业付款操作
     * @param orderNo 企业付款订单编号
     * @return
     */
    @Override
    public String payment(String orderNo) {

        String code = RetCodeConstants.SUCCESS;// 返回编号
        String msg = "";// 返回消息
        try {
            // 验证商户订单号是否存在
            WxPayBill wxPayBill = wxPayBillService.findPayBillByPartnerTradeNo(orderNo);
            LogUtil.info("EnterPrisePaymentServiceFacadeImpl/payment/wxPayBill:{}", JSON.toJSONString(wxPayBill));
            //若定单创建成功，进行接下来的企业付款操作
            if (wxPayBill != null) {
                //校验当前订单是否已经提现完成
                if (SuccessFail.SUCCESS.name().equals(wxPayBill.getReturnCode())
                        && SuccessFail.SUCCESS.name().equals(wxPayBill.getResultCode())) {
                    return JSON.toJSONString(new ReturnBean<>(RetCodeConstants.ERROR, "您已经提现完成了"));
                }
                EnterPrisePaymentBean enterPrisePaymentBean = new EnterPrisePaymentBean();
                enterPrisePaymentBean.setNonce_str(wxPayBill.getNonceStr());// 随机字符串
                enterPrisePaymentBean.setMch_appid(PayConstants.mch_appid);// 支付appid
                enterPrisePaymentBean.setMchid(PayConstants.mchid);// 商户号
                enterPrisePaymentBean.setPartner_trade_no(wxPayBill.getPartnerTradeNo());// 商户订单号
                enterPrisePaymentBean.setOpenid(wxPayBill.getOpenid());// 用户openid
                enterPrisePaymentBean.setCheck_name("NO_CHECK");// 校验用户姓名选项，默认为不校验
                enterPrisePaymentBean.setAmount(String.valueOf(wxPayBill.getAmount().multiply(new BigDecimal(100)).toBigInteger()));// 企业付款金额，单位为分
                enterPrisePaymentBean.setDesc(wxPayBill.getDescription());// 企业付款描述信息
                enterPrisePaymentBean.setSpbill_create_ip(wxPayBill.getIp());// 操作ip
                LogUtil.info("EnterPrisePaymentServiceFacadeImpl/payment/enterPrisePaymentBean:{}",
                        JSON.toJSON(enterPrisePaymentBean));

                // 生成红包数据签名
                String sign = EnterPrisePaymentBean.createEnterPrisePaymentOrderSign(enterPrisePaymentBean);
                LogUtil.info("EnterPrisePaymentServiceFacadeImpl/payment/sign:{}", sign);
                enterPrisePaymentBean.setSign(sign);

                String respXml = BeanToXmlUtil.EnterPrisePaymentToXml(enterPrisePaymentBean);
                LogUtil.info("EnterPrisePaymentServiceFacadeImpl/payment/respXml:{}", respXml);

                Map<String, String> map = null;

                map = wxService.createEnterPrisePayment(respXml);
                // 通信标识
                String return_code = map.get("return_code");
                // 业务结果
                String result_code = map.get("result_code");
                if ("SUCCESS".equals(return_code)) {
                    wxPayBill.setReturnCode(return_code);
                    if ("SUCCESS".equals(result_code)) {
                        wxPayBill.setResultCode(result_code);
                        wxPayBill.setPaymentNo(map.get("payment_no"));
                        wxPayBill.setPaymentTime(DateUtil.parseStrToDate(map.get("payment_time"), "yyyy-MM-dd HH:mm:ss"));
                        msg = "领取成功";
                        LogUtil.info("EnterPrisePaymentServiceFacadeImpl企业付款成功");
                    } else {
                        // 错误代码
                        String err_code = map.get("err_code");
                        // 错误代码描述
                        String err_code_des = map.get("err_code_des");
                        // 系统异常，重试
                        if ("SYSTEMERROR".equals(err_code)) {
                            return payment(orderNo);
                        }
                        code = RetCodeConstants.ERROR;
                        msg = err_code_des;
                        wxPayBill.setErrCode(err_code);
                        wxPayBill.setErrCodeDesc(err_code_des);
                    }
                } else {
                    // 返回信息
                    String return_msg = map.get("return_msg");
                    wxPayBill.setReturnMsg(return_msg);
                    // 业务结果
                    code = RetCodeConstants.ERROR;
                    if ("FAIL".equals(result_code)) {
                        // 错误代码
                        String err_code = map.get("err_code");
                        // 错误代码描述
                        String err_code_des = map.get("err_code_des");
                        msg = return_msg;
                        wxPayBill.setErrCode(err_code);
                        wxPayBill.setErrCodeDesc(err_code_des);
                    }
                }
                LogUtil.info("EnterPrisePaymentServiceFacadeImpl/payment/wxPayBill:{}", JSON.toJSONString(wxPayBill));
                wxPayBillService.update(wxPayBill);
            }
            return JSON.toJSONString(new ReturnBean<>(code, msg));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("EnterPrisePaymentServiceFacadeImpl/payment/exceptioninfo:{}", e.getMessage());
            return JSON.toJSONString(new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC
            ));
        }

    }
}
