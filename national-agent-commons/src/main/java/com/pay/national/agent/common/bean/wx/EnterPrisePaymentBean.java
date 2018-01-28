package com.pay.national.agent.common.bean.wx;

import com.pay.national.agent.common.constants.PayConstants;

import java.util.*;


public class EnterPrisePaymentBean {


    private String mch_appid;// 公众账号appid
    private String mchid;// 商户号
    private String device_info;// 设备号
    private String nonce_str;// 随机字符串
    private String sign;// 签名
    private String partner_trade_no;// 商户订单号
    private String openid;// 用户openid
    private String check_name;// 校验用户姓名选项
    private String re_user_name;// 收款用户姓名
    private String amount;// 金额
    private String desc;// 企业付款描述信息
    private String spbill_create_ip;// Ip地址

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    /**
     * 生成企业付款数据签名
     *
     * @param
     * @return
     */
    public static String createEnterPrisePaymentOrderSign(EnterPrisePaymentBean enterPrisePaymentBean) {
        // 把请求参数打包成数组
        Map<String, String> paramTemp = new HashMap<String, String>();
        paramTemp.put("mch_appid", enterPrisePaymentBean.getMch_appid());
        paramTemp.put("mchid", enterPrisePaymentBean.getMchid());
        paramTemp.put("device_info", enterPrisePaymentBean.getDevice_info());
        paramTemp.put("nonce_str", enterPrisePaymentBean.getNonce_str());
        paramTemp.put("partner_trade_no", enterPrisePaymentBean.getPartner_trade_no());
        paramTemp.put("openid", enterPrisePaymentBean.getOpenid());
        paramTemp.put("check_name", enterPrisePaymentBean.getCheck_name());
        paramTemp.put("re_user_name", enterPrisePaymentBean.getRe_user_name());
        paramTemp.put("amount", enterPrisePaymentBean.getAmount());
        paramTemp.put("desc", enterPrisePaymentBean.getDesc());
        paramTemp.put("spbill_create_ip", enterPrisePaymentBean.getSpbill_create_ip());

        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(paramTemp);
        String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String key = "&key=" + PayConstants.paysign; // 商户支付密钥
        String mysign = com.pay.national.agent.common.utils.DigestUtils.md5(prestr + key).toUpperCase();
        return mysign;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }



}
