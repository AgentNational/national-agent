package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信企业付款流水
 */
public class WxPayBill implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6067331116691631739L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 版本号
     */
    private Integer optimistic;
    /**
     * 外部商户编号
     */
    private String outerAppid;
    /**
     * 外部订单号
     */
    private String outerTradeNo;
    /**
     * 外部openId
     */
    private String outerOpenid;
    /**
     * 商户订单号
     */
    private String partnerTradeNo;
    /**
     * 微信订单号
     */
    private String paymentNo;
    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 校验用户姓名选项
     */
    private String checkName;
    /**
     * 收款用户真实姓名
     */
    private String reUserName;
    /**
     * 付款金额
     */
    private BigDecimal amount;
    /**
     * 付款操作说明信息
     */
    private String desc;
    /**
     * 调用接口的机器Ip地址
     */
    private String ip;
    /**
     * 返回状态码:SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String returnCode;
    /**
     * 返回信息
     */
    private String returnMsg;
    /**
     * 业务结果:SUCCESS/FAIL
     */
    private String resultCode;
    /**
     * 错误代码
     */
    private String errCode;
    /**
     * 错误代码描述
     */
    private String errCodeDesc;
    /**
     * 微信支付成功时间
     */
    private java.util.Date paymentTime;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 备注
     */
    private String remark;

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.Integer getOptimistic() {
        return this.optimistic;
    }

    public void setOptimistic(java.lang.Integer optimistic) {
        this.optimistic = optimistic;
    }

    public java.lang.String getOuterAppid() {
        return this.outerAppid;
    }

    public void setOuterAppid(java.lang.String outerAppid) {
        this.outerAppid = outerAppid;
    }

    public java.lang.String getOuterTradeNo() {
        return this.outerTradeNo;
    }

    public void setOuterTradeNo(java.lang.String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public java.lang.String getOuterOpenid() {
        return this.outerOpenid;
    }

    public void setOuterOpenid(java.lang.String outerOpenid) {
        this.outerOpenid = outerOpenid;
    }

    public java.lang.String getPartnerTradeNo() {
        return this.partnerTradeNo;
    }

    public void setPartnerTradeNo(java.lang.String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public java.lang.String getPaymentNo() {
        return this.paymentNo;
    }

    public void setPaymentNo(java.lang.String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public java.lang.String getOpenid() {
        return this.openid;
    }

    public void setOpenid(java.lang.String openid) {
        this.openid = openid;
    }

    public java.lang.String getNonceStr() {
        return this.nonceStr;
    }

    public void setNonceStr(java.lang.String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public java.lang.String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(java.lang.String checkName) {
        this.checkName = checkName;
    }

    public java.lang.String getReUserName() {
        return this.reUserName;
    }

    public void setReUserName(java.lang.String reUserName) {
        this.reUserName = reUserName;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public java.lang.String getDesc() {
        return this.desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public java.lang.String getIp() {
        return this.ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    public java.lang.String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(java.lang.String returnCode) {
        this.returnCode = returnCode;
    }

    public java.lang.String getReturnMsg() {
        return this.returnMsg;
    }

    public void setReturnMsg(java.lang.String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public java.lang.String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }

    public java.lang.String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(java.lang.String errCode) {
        this.errCode = errCode;
    }

    public java.lang.String getErrCodeDesc() {
        return this.errCodeDesc;
    }

    public void setErrCodeDesc(java.lang.String errCodeDesc) {
        this.errCodeDesc = errCodeDesc;
    }

    public java.util.Date getPaymentTime() {
        return this.paymentTime;
    }

    public void setPaymentTime(java.util.Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
}