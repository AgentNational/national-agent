package com.pay.national.agent.model.entity;

import java.util.Date;

public class WxPublicPrepayBill {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.OPTIMISTIC
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private Integer optimistic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.OUTER_TRADE_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String outerTradeNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.PREPAY_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String prepayId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.PARTNER_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String partnerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.BUSI_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String busiNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.BUSI_NOTIFY_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String busiNotifyUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.BUSI_SYNC_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String busiSyncUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.OPENID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String openid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.DEVICE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String deviceInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.NONCE_STR
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String nonceStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.BODY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String body;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.DETAIL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String detail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.ATTACH
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String attach;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.FEE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String feeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.TOTAL_FEE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private Integer totalFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.SPBILL_CREATE_IP
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String spbillCreateIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.TIME_START
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String timeStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.TIME_EXPIRE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String timeExpire;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.GOODS_TAG
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String goodsTag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.TRADE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String tradeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.PRODUCT_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.LIMIT_PAY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String limitPay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.SCENE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String sceneInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.RETURN_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String returnCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.RESULT_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String resultCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.CODE_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String codeUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.CREATE_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.MODIFY_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_public_prepay_bill.REMARK
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.ID
     *
     * @return the value of wx_public_prepay_bill.ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.ID
     *
     * @param id the value for wx_public_prepay_bill.ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.OPTIMISTIC
     *
     * @return the value of wx_public_prepay_bill.OPTIMISTIC
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public Integer getOptimistic() {
        return optimistic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.OPTIMISTIC
     *
     * @param optimistic the value for wx_public_prepay_bill.OPTIMISTIC
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.OUTER_TRADE_NO
     *
     * @return the value of wx_public_prepay_bill.OUTER_TRADE_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.OUTER_TRADE_NO
     *
     * @param outerTradeNo the value for wx_public_prepay_bill.OUTER_TRADE_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.PREPAY_ID
     *
     * @return the value of wx_public_prepay_bill.PREPAY_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getPrepayId() {
        return prepayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.PREPAY_ID
     *
     * @param prepayId the value for wx_public_prepay_bill.PREPAY_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.PARTNER_ID
     *
     * @return the value of wx_public_prepay_bill.PARTNER_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.PARTNER_ID
     *
     * @param partnerId the value for wx_public_prepay_bill.PARTNER_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.BUSI_NO
     *
     * @return the value of wx_public_prepay_bill.BUSI_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.BUSI_NO
     *
     * @param busiNo the value for wx_public_prepay_bill.BUSI_NO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.BUSI_NOTIFY_URL
     *
     * @return the value of wx_public_prepay_bill.BUSI_NOTIFY_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getBusiNotifyUrl() {
        return busiNotifyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.BUSI_NOTIFY_URL
     *
     * @param busiNotifyUrl the value for wx_public_prepay_bill.BUSI_NOTIFY_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setBusiNotifyUrl(String busiNotifyUrl) {
        this.busiNotifyUrl = busiNotifyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.BUSI_SYNC_URL
     *
     * @return the value of wx_public_prepay_bill.BUSI_SYNC_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getBusiSyncUrl() {
        return busiSyncUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.BUSI_SYNC_URL
     *
     * @param busiSyncUrl the value for wx_public_prepay_bill.BUSI_SYNC_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setBusiSyncUrl(String busiSyncUrl) {
        this.busiSyncUrl = busiSyncUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.OPENID
     *
     * @return the value of wx_public_prepay_bill.OPENID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.OPENID
     *
     * @param openid the value for wx_public_prepay_bill.OPENID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.DEVICE_INFO
     *
     * @return the value of wx_public_prepay_bill.DEVICE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.DEVICE_INFO
     *
     * @param deviceInfo the value for wx_public_prepay_bill.DEVICE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.NONCE_STR
     *
     * @return the value of wx_public_prepay_bill.NONCE_STR
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getNonceStr() {
        return nonceStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.NONCE_STR
     *
     * @param nonceStr the value for wx_public_prepay_bill.NONCE_STR
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.BODY
     *
     * @return the value of wx_public_prepay_bill.BODY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.BODY
     *
     * @param body the value for wx_public_prepay_bill.BODY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.DETAIL
     *
     * @return the value of wx_public_prepay_bill.DETAIL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.DETAIL
     *
     * @param detail the value for wx_public_prepay_bill.DETAIL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.ATTACH
     *
     * @return the value of wx_public_prepay_bill.ATTACH
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getAttach() {
        return attach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.ATTACH
     *
     * @param attach the value for wx_public_prepay_bill.ATTACH
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.FEE_TYPE
     *
     * @return the value of wx_public_prepay_bill.FEE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.FEE_TYPE
     *
     * @param feeType the value for wx_public_prepay_bill.FEE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.TOTAL_FEE
     *
     * @return the value of wx_public_prepay_bill.TOTAL_FEE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.TOTAL_FEE
     *
     * @param totalFee the value for wx_public_prepay_bill.TOTAL_FEE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.SPBILL_CREATE_IP
     *
     * @return the value of wx_public_prepay_bill.SPBILL_CREATE_IP
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.SPBILL_CREATE_IP
     *
     * @param spbillCreateIp the value for wx_public_prepay_bill.SPBILL_CREATE_IP
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.TIME_START
     *
     * @return the value of wx_public_prepay_bill.TIME_START
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getTimeStart() {
        return timeStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.TIME_START
     *
     * @param timeStart the value for wx_public_prepay_bill.TIME_START
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.TIME_EXPIRE
     *
     * @return the value of wx_public_prepay_bill.TIME_EXPIRE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getTimeExpire() {
        return timeExpire;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.TIME_EXPIRE
     *
     * @param timeExpire the value for wx_public_prepay_bill.TIME_EXPIRE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.GOODS_TAG
     *
     * @return the value of wx_public_prepay_bill.GOODS_TAG
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getGoodsTag() {
        return goodsTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.GOODS_TAG
     *
     * @param goodsTag the value for wx_public_prepay_bill.GOODS_TAG
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.TRADE_TYPE
     *
     * @return the value of wx_public_prepay_bill.TRADE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.TRADE_TYPE
     *
     * @param tradeType the value for wx_public_prepay_bill.TRADE_TYPE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.PRODUCT_ID
     *
     * @return the value of wx_public_prepay_bill.PRODUCT_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.PRODUCT_ID
     *
     * @param productId the value for wx_public_prepay_bill.PRODUCT_ID
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.LIMIT_PAY
     *
     * @return the value of wx_public_prepay_bill.LIMIT_PAY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getLimitPay() {
        return limitPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.LIMIT_PAY
     *
     * @param limitPay the value for wx_public_prepay_bill.LIMIT_PAY
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.SCENE_INFO
     *
     * @return the value of wx_public_prepay_bill.SCENE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getSceneInfo() {
        return sceneInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.SCENE_INFO
     *
     * @param sceneInfo the value for wx_public_prepay_bill.SCENE_INFO
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.RETURN_CODE
     *
     * @return the value of wx_public_prepay_bill.RETURN_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.RETURN_CODE
     *
     * @param returnCode the value for wx_public_prepay_bill.RETURN_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.RESULT_CODE
     *
     * @return the value of wx_public_prepay_bill.RESULT_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.RESULT_CODE
     *
     * @param resultCode the value for wx_public_prepay_bill.RESULT_CODE
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.CODE_URL
     *
     * @return the value of wx_public_prepay_bill.CODE_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.CODE_URL
     *
     * @param codeUrl the value for wx_public_prepay_bill.CODE_URL
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.CREATE_TIME
     *
     * @return the value of wx_public_prepay_bill.CREATE_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.CREATE_TIME
     *
     * @param createTime the value for wx_public_prepay_bill.CREATE_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.MODIFY_TIME
     *
     * @return the value of wx_public_prepay_bill.MODIFY_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.MODIFY_TIME
     *
     * @param modifyTime the value for wx_public_prepay_bill.MODIFY_TIME
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_public_prepay_bill.REMARK
     *
     * @return the value of wx_public_prepay_bill.REMARK
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_public_prepay_bill.REMARK
     *
     * @param remark the value for wx_public_prepay_bill.REMARK
     *
     * @mbggenerated Sun Jan 28 11:25:11 GMT+08:00 2018
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public WxPublicPrepayBill() {
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