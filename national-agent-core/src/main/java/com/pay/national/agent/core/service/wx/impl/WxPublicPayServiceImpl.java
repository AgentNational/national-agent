package com.pay.national.agent.core.service.wx.impl;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.constants.PayConstants;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.DigestUtils;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.wx.RequestUtil;
import com.pay.national.agent.common.utils.wx.UUIDHexGenerator;
import com.pay.national.agent.common.utils.wx.UUIDUtils;
import com.pay.national.agent.common.utils.wx.WeixinPayUtil;
import com.pay.national.agent.core.dao.wx.WxPublicPayBillMapper;
import com.pay.national.agent.core.dao.wx.WxPublicPayDetailMapper;
import com.pay.national.agent.core.dao.wx.WxPublicPrepayBillMapper;
import com.pay.national.agent.core.service.wx.WxPublicPayService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.entity.WxPublicPayBill;
import com.pay.national.agent.model.entity.WxPublicPayDetail;
import com.pay.national.agent.model.entity.WxPublicPrepayBill;
import com.pay.national.agent.model.enums.SuccessFail;
import com.pay.national.agent.model.enums.WxPubPayType;
import com.pay.national.agent.model.enums.WxPubTradeType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service("wxPublicPayService")
public class WxPublicPayServiceImpl implements WxPublicPayService{

    @Resource
    private WxService wxService;

    @Resource
    private WxPublicPayBillMapper wxPublicPayBillMapper;

    @Resource
    private WxPublicPrepayBillMapper wxPublicPrepayBillMapper;

    @Resource
    private WxPublicPayDetailMapper wxPublicPayDetailMapper;

    @Override
    public Map<String, String> createPayBill(Map<String, String> params) {

        LogUtil.info("微信下单请求参数:{}", JSON.toJSONString(params));
        Map<String, String> result = new HashMap<String, String>();
        String redirectURL = params.get("redirectURL");// 支付成功跳转地址
        String amount = params.get("amount");// 订单金额
        String ip = params.get("ip");// 访问ip
        String payNotifyUrl = params.get("payNotifyUrl");// 支付成功微信回调地址
        String openId = params.get("openId");//
        try {
            // 生成微信支付商户订单号
            String outerTradeNo = UUIDHexGenerator.getInstance().generate();
            WxPublicPrepayBill publicPrepayBill = findWxPublicPrepayByOuterTradeNo(outerTradeNo);
            if (publicPrepayBill != null) {
                // 商户订单号重复，重新下单
                return createPayBill(params);
            }

            Float totalFee = Float.valueOf(amount);
            // 微信支付默认单位为分 而全民代理默认单位为元 所以需要乘100总金额
            Integer total_fee = Math.round(totalFee * 100);
            // 商品描述
            String body = "全民代理";
            // 随机数
            String nonceStr = UUIDUtils.getUUID();

            SortedMap<String, String> packageParams = new TreeMap<String, String>();
            packageParams.put("appid", PayConstants.mch_appid);
            packageParams.put("mch_id", PayConstants.mchid);
            packageParams.put("nonce_str", nonceStr);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", outerTradeNo);
            packageParams.put("total_fee", total_fee + "");
            packageParams.put("spbill_create_ip", ip);
            packageParams.put("notify_url", payNotifyUrl);
            packageParams.put("trade_type", WxPubTradeType.JSAPI.name());
            packageParams.put("openid", openId);
            // 获取验签值
            String sign = WeixinPayUtil.createSign(packageParams);
            String xml = WeixinPayUtil.parseString2Xml(packageParams, sign);
            LogUtil.info("微信支付请求报文1：{}", xml);

            // 调用微信下单
            String jsonStr = wxService.createWxPayOrder(xml);
            Map<String, String> map = WeixinPayUtil.doXMLParse(jsonStr);
            LogUtil.info("微信支付商户订单号：{},返回结果：{}", JSON.toJSONString(map));
            String prepayId = "";
            String returnCode = map.get("return_code");
            String returnMsg = map.get("return_msg");
            String resultCode = null;
            String errCode = null;
            String errCodeDes = null;
            result.put("returnCode", returnCode);
            // 通信标识成功
            if ("SUCCESS".equals(returnCode)) {
                resultCode = map.get("result_code");
                result.put("resultCode", resultCode);
                if ("SUCCESS".equals(resultCode)) {
                    prepayId = map.get("prepay_id");
                    LogUtil.info("微信支付商户订单号：{},返回预支付交易标识：{}", outerTradeNo, prepayId);
                } else {
                    errCode = map.get("err_code");
                    errCodeDes = map.get("err_code_des");
                    result.put("returnMsg", errCodeDes);
                }
            } else {
                result.put("returnMsg", returnMsg);
            }
            // 生成微信预订单
            WxPublicPrepayBill wxPublicPrepayBill = new WxPublicPrepayBill(outerTradeNo, prepayId, null, null,
                    null, redirectURL, openId, null, nonceStr, body, null, null, null, total_fee, ip, null, null,
                    null, WxPubTradeType.JSAPI.name(), null, null, null, returnCode, resultCode, null, null);
            // 预订单详情
            WxPublicPayDetail wxPublicPayDetail = new WxPublicPayDetail(outerTradeNo, returnCode, returnMsg, resultCode,
                    errCode, errCodeDes, WxPubPayType.PAY_PREPAY.name(), null);
            insertWxPublicPrepayBill(wxPublicPrepayBill, wxPublicPayDetail);

            SortedMap<String, String> finalpackage = new TreeMap<String, String>();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String packages = "prepay_id=" + prepayId;
            finalpackage.put("appId", PayConstants.mch_appid);
            finalpackage.put("timeStamp", timestamp);
            finalpackage.put("nonceStr", nonceStr);
            finalpackage.put("package", packages);
            finalpackage.put("signType", "MD5");
            String finalsign = WeixinPayUtil.createSign(finalpackage);

            result.put("timeStamp", timestamp);
            result.put("nonceStr", nonceStr);
            result.put("packageValue", packages);
            result.put("sign", finalsign);
            result.put("outerTradeNo", outerTradeNo);
        } catch (Exception e1) {
            LogUtil.error("微信支付异常，商户订单号：{}，错误信息：{}", e1.getMessage());
            result.put("returnCode", SuccessFail.FAIL.name());
            result.put("returnMsg", "系统异常");
        }
        return result;
    }


    /**
     * 通过订单号查询
     * @param outerTradeNo
     * @return
     */
    private  WxPublicPrepayBill findWxPublicPrepayByOuterTradeNo(String outerTradeNo) {
        return wxPublicPrepayBillMapper.findWxPublicPrepayByOuterTradeNo(outerTradeNo);
    }

    private void insertWxPublicPrepayBill(WxPublicPrepayBill wxPublicPrepayBill, WxPublicPayDetail wxPublicPayDetail) {
        wxPublicPrepayBillMapper.insert(wxPublicPrepayBill);
        wxPublicPayDetailMapper.insert(wxPublicPayDetail);
    }

    @Override
    public Map<String, String> updateNotifyPay(String notifyXml) {

        Map<String, String> map = new HashMap<String, String>();
        try {
            Map<String, String> resultMap = WeixinPayUtil.doXMLParse(notifyXml);
            if (checkSign(resultMap)) {
                LogUtil.info("微信支付异步回调返回报文解析结果：{}", JSON.toJSONString(resultMap));
                if (resultMap != null && resultMap.size() > 0) {
                    LogUtil.info("微信支付异步回调验签成功");
                    // 获得返回结果
                    String returnCode = resultMap.get("return_code");
                    String returnMsg = map.get("return_msg");
                    String resultCode = null;
                    String errCode = null;
                    String errCodeDes = null;
                    String outerTradeNo = null;
                    // 通信成功
                    if ("SUCCESS".equals(returnCode)) {
                        resultCode = resultMap.get("result_code");
                        outerTradeNo = resultMap.get("out_trade_no");
                        // 支付异步回调成功
                        if ("SUCCESS".equals(resultCode)) {
                            String transactionId = resultMap.get("transaction_id");
                            String bankType = resultMap.get("bank_type");
                            String totalFee = resultMap.get("total_fee");
                            String timeEnd = resultMap.get("time_end");
                            LogUtil.info("微信支付异步回调商户订单号：{},微信订单号：{}", outerTradeNo, transactionId);
                            // 验证该商户订单号下是否存在与支付订单
                            WxPublicPrepayBill publicPrepayBill =
                                    findWxPublicPrepayByOuterTradeNo(outerTradeNo);
                            LogUtil.info("商户订单号:{},预支付订单信息:{}", outerTradeNo, JSON.toJSONString(publicPrepayBill));
                            if (publicPrepayBill != null) {
                                // 验证该商户订单号下是否存在支付成功订单
                                WxPublicPayBill publicPayBill = findWxPublicPayByTradeno(outerTradeNo);
                                LogUtil.info("商户订单号:{},支付订单信息:{}", outerTradeNo, JSON.toJSONString(publicPayBill));
                                // 封装支付订单bean
                                WxPublicPayBill wxPublicPayBill = new WxPublicPayBill(publicPrepayBill.getId(),
                                        outerTradeNo, transactionId, returnCode, resultCode, bankType,
                                        Integer.valueOf(totalFee), DateUtil.parseStrToDate(timeEnd, "yyyyMMddHHmmss"),
                                        null);
                                // 封装支付详情bean
                                WxPublicPayDetail wxPublicPayDetail = new WxPublicPayDetail(outerTradeNo, returnCode,
                                        returnMsg, resultCode, errCode, errCodeDes, WxPubPayType.PAY_NOTIFY.name(),
                                        null);
                                // 不存在支付订单，进行增加操作，否则就行修改，并且记录订单详情
                                if (publicPayBill == null) {
                                    insertWxPublicPayBill(wxPublicPayBill, wxPublicPayDetail);
                                } else {
                                    wxPublicPayBill.setId(publicPayBill.getId());
                                    wxPublicPayBill.setCreateTime(publicPayBill.getCreateTime());
                                    updateWxPublicPayBill(wxPublicPayBill, wxPublicPayDetail);
                                }
                                try {
                                    boolean flag = toNotifyBusi(publicPrepayBill);
                                    if (flag) {
                                        map.put("resultCode", resultCode);
                                    }
                                } catch (Exception e) {
                                    LogUtil.error("微信支付异步回调业务方异常，异常信息：{}", e.getMessage());
                                }
                            } else {
                                LogUtil.info("微信支付异步回调预支付订单不存在，商户订单号：{}", outerTradeNo);
                            }
                            return map;
                        } else {
                            LogUtil.info("微信支付异步回调商户订单号：{},失败码：{},失败原因：{}", outerTradeNo, errCode, errCodeDes);
                        }
                    } else {
                        LogUtil.info("微信支付异步回调通信异常，失败原因：{}", returnMsg);
                    }
                    WxPublicPayDetail wxPublicPayDetail = new WxPublicPayDetail(outerTradeNo, returnCode, returnMsg,
                            resultCode, errCode, errCodeDes, WxPubPayType.PAY_NOTIFY.name(), null);
                    insertWxPublicPayDetail(wxPublicPayDetail);
                }
            } else {
                LogUtil.info("微信支付异步回调验签失败，数据可能篡改");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("微信支付异步回调异常，异常信息：{}", e.getMessage());
        }
        map.put("resultCode", SuccessFail.FAIL.name());
        return map;

    }

    private void insertWxPublicPayDetail(WxPublicPayDetail wxPublicPayDetail) {
        wxPublicPayDetailMapper.insert(wxPublicPayDetail);
    }

    private void updateWxPublicPayBill(WxPublicPayBill wxPublicPayBill, WxPublicPayDetail wxPublicPayDetail) {
        wxPublicPayDetailMapper.updateByPrimaryKey(wxPublicPayDetail);
        wxPublicPayBillMapper.updateByPrimaryKey(wxPublicPayBill);
    }

    private void insertWxPublicPayBill(WxPublicPayBill wxPublicPayBill, WxPublicPayDetail wxPublicPayDetail) {
        wxPublicPayDetailMapper.insert(wxPublicPayDetail);
        wxPublicPayBillMapper.insert(wxPublicPayBill);
    }

    private WxPublicPayBill findWxPublicPayByTradeno(String outerTradeNo) {
        return wxPublicPayBillMapper.findWxPublicPayByTradeno(outerTradeNo);
    }

    /**
     * @Description 验证回调参数是否正确
     * @param resultMap
     * @return
     * @see
     */
    private boolean checkSign(Map<String, String> resultMap) {
        String sign = resultMap.get("sign");
        resultMap.remove("sign");
        String mapToStrSort = RequestUtil.MapToStrSort(resultMap);
        String param = mapToStrSort + "&key=" + PayConstants.paysign;
        String signEnd = DigestUtils.md5(param).toUpperCase();
        if (sign.equals(signEnd)) {
            return true;
        }
        return false;
    }


    /**
     * @Description 异步通知业务方
     * @param
     * @see
     */
    private boolean toNotifyBusi(WxPublicPrepayBill prepayBill) {
        StringBuffer sb = new StringBuffer();
        sb.append("handlerResult=0000&tradeOrderCode=").append(prepayBill.getOuterTradeNo());
        sb.append("&outOrderId=").append(prepayBill.getBusiNo());
        sb.append("&partner=").append(prepayBill.getPartnerId());
        sb.append("&amount=").append(new BigDecimal(prepayBill.getTotalFee()).divide(new BigDecimal(100)));
        sb.append("&").append(prepayBill.getAttach());
        // 异步通知业务方订单信息
        String result = HttpClientUtil.sendGet(prepayBill.getBusiNotifyUrl(), sb.toString());
        LogUtil.info("微信支付同步通知业务下单方，订单号：{}返回结果：{}", prepayBill.getOuterTradeNo(), result);
        WxPublicPayDetail wxPublicPayDetail = new WxPublicPayDetail(prepayBill.getOuterTradeNo(), result, result,
                result, result, result, WxPubPayType.PAY_NOTIFY_BUSI.name(), null);
        insertWxPublicPayDetail(wxPublicPayDetail);
        if ("SUCCESS".equals(result)) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> toCheckWxpay(String outerTradeNo) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String nonce_str = UUIDUtils.getUUID();
            SortedMap<String, String> storeMap = new TreeMap<String, String>();
            storeMap.put("out_trade_no", outerTradeNo); // 商户 后台的贸易单号
            storeMap.put("appid", PayConstants.mch_appid); // appid
            storeMap.put("mch_id", PayConstants.mchid); // 商户号
            storeMap.put("nonce_str", nonce_str); // 随机数
            String sign = WeixinPayUtil.createSign(storeMap);

            String xml = WeixinPayUtil.parseString2Xml(storeMap, sign);
            String resultMsg = wxService.getWxPayOrder(xml);
            Map<String, String> resultMap = WeixinPayUtil.doXMLParse(resultMsg);
            LogUtil.info("微信支付同步查询订单号：{},返回结果：{}", outerTradeNo, JSON.toJSONString(resultMap));
            String returnCode = resultMap.get("return_code");

            if ("SUCCESS".equals(returnCode)) {
                String resultCode = resultMap.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    WxPublicPrepayBill prepayBill = findWxPublicPrepayByOuterTradeNo(outerTradeNo);
                    if (prepayBill != null) {
                        map.put("handlerResult", "0000");
                        StringBuffer sb = new StringBuffer(prepayBill.getBusiSyncUrl());
                        sb.append("?handlerResult=").append("0000").append("&").append(prepayBill.getAttach());
                        map.put("redirectUrl", sb.toString());
                        return map;
                    }
                } else {
                    String err_code = (String) resultMap.get("err_code");
                    String err_code_des = (String) resultMap.get("err_code_des");
                    LogUtil.info("微信支付同步查询接口，订单号：{},错误码：{},错误信息:{}", outerTradeNo, err_code, err_code_des);
                }
            } else {
                LogUtil.info("微信支付同步查询通信异常，失败原因：{}", resultMap.get("return_msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("微信支付同步查询异常，异常信息：{}", e.getMessage());
        }
        map.put("handlerResult", "9999");
        return map;
    }
}
