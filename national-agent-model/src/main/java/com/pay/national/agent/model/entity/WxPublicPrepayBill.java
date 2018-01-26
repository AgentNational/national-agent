package com.pay.national.agent.model.entity;

import java.io.Serializable;

/**
 * 微信支付预付单信息
 */
public class WxPublicPrepayBill implements Serializable{


    /**
     *
     */
    private static final long serialVersionUID = -1475103036569393253L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 版本号
     */
    private Integer optimistic;
    /**
     * 商户订单号
     */
    private String outerTradeNo;
    /**
     * 预支付交易会话标识
     */
    private String prepayId;
    /**
     * 业务商户支付单号
     */
    private String partnerId;
    /**
     * 业务订单号
     */
    private String busiNo;
    /**
     * 业务通知地址
     */
    private String busiNotifyUrl;
    /**
     * 业务同步返回地址
     */
    private String busiSyncUrl;
    /**
     * 微信用户唯一标示
     */
    private String openid;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 标价币种
     */
    private String feeType;
    /**
     * 标价金额单位分
     */
    private Integer totalFee;
    /**
     * 终端ip
     */
    private String spbillCreateIp;
    /**
     * 交易起始时间格式为yyyyMMddHHmmss
     */
    private String timeStart;
    /**
     * 交易结束时间（最短失效时间间隔必须大于5分钟）
     */
    private String timeExpire;
    /**
     * 订单优惠标记
     */
    private String goodsTag;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 商品id
     */
    private String productId;
    /**
     * 指定支付方式（上传此参数no_credit--可限制用户不能使用信用卡支付）
     */
    private String limitPay;
    /**
     * 场景信息
     */
    private String sceneInfo;
    /**
     * 通信标识（SUCCESS/FAIL）
     */
    private String returnCode;
    /**
     * 业务结果（SUCCESS/FAIL）
     */
    private String resultCode;
    /**
     * 交易为扫码交易返回支付二维码
     */
    private String codeUrl;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 修改时间
     */
    private java.util.Date modifyTime;
    /**
     * 备注
     */
    private String remark;

    public WxPublicPrepayBill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public String getBusiNotifyUrl() {
        return busiNotifyUrl;
    }

    public void setBusiNotifyUrl(String busiNotifyUrl) {
        this.busiNotifyUrl = busiNotifyUrl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBusiSyncUrl() {
        return busiSyncUrl;
    }

    public void setBusiSyncUrl(String busiSyncUrl) {
        this.busiSyncUrl = busiSyncUrl;
    }

    public WxPublicPrepayBill(String outerTradeNo, String prepayId, String partnerId, String busiNo,
                              String busiNotifyUrl, String busiSyncUrl, String openid, String deviceInfo, String nonceStr, String body,
                              String detail, String attach, String feeType, Integer totalFee, String spbillCreateIp, String timeStart,
                              String timeExpire, String goodsTag, String tradeType, String productId, String limitPay, String sceneInfo,
                              String returnCode, String resultCode, String codeUrl, String remark) {
        super();
        this.outerTradeNo = outerTradeNo;
        this.prepayId = prepayId;
        this.partnerId = partnerId;
        this.busiNo = busiNo;
        this.busiNotifyUrl = busiNotifyUrl;
        this.busiSyncUrl = busiSyncUrl;
        this.openid = openid;
        this.deviceInfo = deviceInfo;
        this.nonceStr = nonceStr;
        this.body = body;
        this.detail = detail;
        this.attach = attach;
        this.feeType = feeType;
        this.totalFee = totalFee;
        this.spbillCreateIp = spbillCreateIp;
        this.timeStart = timeStart;
        this.timeExpire = timeExpire;
        this.goodsTag = goodsTag;
        this.tradeType = tradeType;
        this.productId = productId;
        this.limitPay = limitPay;
        this.sceneInfo = sceneInfo;
        this.returnCode = returnCode;
        this.resultCode = resultCode;
        this.codeUrl = codeUrl;
        this.remark = remark;
    }


}
