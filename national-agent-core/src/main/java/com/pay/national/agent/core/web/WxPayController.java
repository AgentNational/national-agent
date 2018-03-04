package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.annotation.NeedOpenId;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.common.utils.wx.CodecUtil;
import com.pay.national.agent.common.utils.wx.RequestUtil;
import com.pay.national.agent.common.utils.wx.XmlUtil;
import com.pay.national.agent.core.service.wx.EnterPrisePaymentService;
import com.pay.national.agent.core.service.wx.WxPublicPayService;
import com.pay.national.agent.model.enums.SuccessFail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/wxpay")
public class WxPayController {

    private static final String paySecret = "FBBF29289BCB4AE604735470B6A638A8";
    private static String url;// 项目路径
    static {
        PropertiesLoader propertyUtil = new PropertiesLoader("system.properties");
        url = propertyUtil.getProperty("pay.weixin.system.url");
    }


    /**
     * 企业付款service
     */
    @Resource
    private EnterPrisePaymentService enterPrisePaymentService;

    /**
     * 微信支付service
     */
    @Resource
    private WxPublicPayService wxPublicPayService;

    /**
     * @Description 跳转到支付页面
     * @param model
     * @return
     * @see
     */
    @RequestMapping("/topay")
    @NeedOpenId(needOpenId = true, isLoginFlag = false)
    public String toPay(HttpServletRequest request,Model model) {
        String openId = (String) WebUtils.getSessionAttribute(request, WeiXinConstant.sessinoOpenIdKey);
        model.addAttribute("openId",openId);
        return "/wxpay/topay";
    }
    /**
     * @Description jsp统一支付下单
     * @param request
     * @param response
     * @param model
     * @return
     * @see
     */
    @RequestMapping(value = "/pay")
    @NeedOpenId(needOpenId = true, isLoginFlag = false)
    public String toPay(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam Map<String, String> params) {
        LogUtil.info("统一下单请求参数：{}", JSON.toJSONString(params));
        String orderId = params.get("orderId");
        if(StringUtils.isBlank(orderId)){
            return "/wxpay/payerror";
        }
        try {
            // 下单之前进行参数验签
            if (true/*checkSign(params)*/) {
                String openId = (String) WebUtils.getSessionAttribute(request, WeiXinConstant.sessinoOpenIdKey);
                LogUtil.info("支付openId:{}", openId);
                String ip = request.getRemoteAddr();// 订单生成的机器 IP
                String payNotifyUrl = url + "/wxpay/notify";// 支付成功回调地址
                String redirectURL = url +"/jump/jsp?url=/wxpay/payResult";
                params.put("openId", openId);
                params.put("ip", ip);
                params.put("redirectURL",redirectURL);
                params.put("payNotifyUrl", payNotifyUrl);

                // 调用微信统一下单
                Map<String, String> map = wxPublicPayService.createPayBill(params);
                if (map != null) {
                    String returnCode = map.get("returnCode");
                    String resultCode = map.get("resultCode");
                    // 下单成功，唤起微信支付密码框
                    if (SuccessFail.SUCCESS.name().equals(returnCode)
                            && SuccessFail.SUCCESS.name().equals(resultCode)) {
                        model.addAttribute("timeStamp", map.get("timeStamp"));
                        model.addAttribute("nonceStr", map.get("nonceStr"));
                        model.addAttribute("packageValue", map.get("packageValue"));
                        model.addAttribute("sign", map.get("sign"));
                        model.addAttribute("outerTradeNo", map.get("outerTradeNo"));
                        StringBuffer cancelUrl = new StringBuffer(params.get("redirectURL"));// 支付过程中点击取消跳转地址
                        cancelUrl.append("?handlerResult=9999&returnParam=").append(params.get("returnParam"));
                        model.addAttribute("cancelUrl", cancelUrl);
                        return "/wxpay/pay";
                    } else {
                        String returnMsg = map.get("returnMsg");
                        model.addAttribute("returnMsg", returnMsg);
                        return "/wxpay/payerror";
                    }
                }
            } else {
                model.addAttribute("returnMsg", "请求参数不正确");
                return "/wxpay/payerror";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info("微信支付下单dubbo服务异常", e);
            model.addAttribute("returnMsg", "系统异常");
        }
        return "/wxpay/payerror";
    }

    /**
     * @Description 支付前将请求参数进行验签，以防止篡改信息
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @see
     */
    private boolean checkSign(Map<String, String> params) throws UnsupportedEncodingException {
        String sign = params.get("sign");
        params.remove("sign");
        params.remove("code");// 微信自带参数，不参与验签，警惕警惕警惕
        params.remove("state");// 警惕警惕警惕，介里是个大坑
        String mapToStrSort = RequestUtil.MapToStrSort(params);
        String param = mapToStrSort + paySecret;
        String signEnd = CodecUtil.md5(param, "utf-8", true);
        if (sign.equals(signEnd)) {
            return true;
        }
        return false;
    }

    /**
     * 微信异步回调，不会跳转页面
     *
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/notify")
    public @ResponseBody
    String notify(HttpServletRequest request, HttpServletResponse response) {
        // 解析微信回调参数内容
        String notifyXml = XmlUtil.getXmlRequest(request);
        LogUtil.info("微信支付异步回调返回报文：{}", notifyXml);
        try {
            // 调用服务更新支付相应订单
            Map<String, String> resultMap = wxPublicPayService.updateNotifyPay(notifyXml);
            String resultCode = resultMap.get("resultCode");
            if (SuccessFail.SUCCESS.name().equals(resultCode)) {
                return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info("微信支付异步回调dubbo服务异常", e);

        }
        return null;
    }

    /**
     * 支付成功同步回调，查询支付结果并跳转结果页面
     *
     * @param request
     * @param response
     * @param
     * @return
     * @throws
     */
    @RequestMapping("/success")
    public String toWxPaySuccess(HttpServletRequest request, HttpServletResponse response) {
        String outerTradeNo = request.getParameter("outerTradeNo");
        LogUtil.info("微信支付同步接口，返回订单号：{}", outerTradeNo);
        try {
            // 支付完成，同步查询支付结果，并跳转到相应页面
            Map<String, String> map = wxPublicPayService.toCheckWxpay(outerTradeNo);
            if (map != null) {
                String handlerResult = map.get("handlerResult");
                if ("0000".equals(handlerResult)) {
                    String redirectUrl = map.get("redirectUrl");
                    return "redirect:" + redirectUrl;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info("微信支付同步dubbo服务异常", e);
        }
        return "/wxpay/payerror";
    }







}
