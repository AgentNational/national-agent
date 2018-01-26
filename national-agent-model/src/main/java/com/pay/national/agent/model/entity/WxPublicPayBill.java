package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信支付订单信息
 */
public class WxPublicPayBill implements Serializable{



    /**
     *
     */
    private static final long serialVersionUID = -1475103036569393253L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 主键
     */
    private Long prepayBillId;
    /**
     * 版本号
     */
    private Integer optimistic;
    /**
     * 商户订单号
     */
    private String outerTradeNo;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 通信标识（SUCCESS/FAIL）
     */
    private String returnCode;
    /**
     * 业务结果（SUCCESS/FAIL）
     */
    private String resultCode;
    /**
     * 付款银行
     */
    private String bankType;
    /**
     * 订单金额
     */
    private Integer totalFee;
    /**
     * 支付完成时间
     */
    private java.util.Date timeEnd;
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

    public WxPublicPayBill() {
    }

    public Long getPrepayBillId() {
        return prepayBillId;
    }

    public void setPrepayBillId(Long prepayBillId) {
        this.prepayBillId = prepayBillId;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public java.util.Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(java.util.Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public WxPublicPayBill(Long prepayBillId, String outerTradeNo, String transactionId, String returnCode,
                           String resultCode, String bankType, Integer totalFee, Date timeEnd, String remark) {
        super();
        this.prepayBillId = prepayBillId;
        this.outerTradeNo = outerTradeNo;
        this.transactionId = transactionId;
        this.returnCode = returnCode;
        this.resultCode = resultCode;
        this.bankType = bankType;
        this.totalFee = totalFee;
        this.timeEnd = timeEnd;
        this.remark = remark;
    }




}
