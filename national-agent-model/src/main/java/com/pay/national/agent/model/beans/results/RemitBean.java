package com.pay.national.agent.model.beans.results;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 *
 * 出款返回bean
 * @author shuyan.qi
 * @date 2018/1/30
 */
public class RemitBean {
    /**
    *付款单号
    */
    private String payBillNo;
    /**
     *状态
     */
    private String payStatus;
    /**
     *提现金额
     */
    private double amount;

    /**
     * 实际出款金额
     */
    private double remitAmount;
    /**
     * 费用
     */
    private double fee;
    /**
     *押金
     */
    private Double yajin;
    /**
     *交易时间
     */
    private Date transTime;

    public String getPayBillNo() {
        return payBillNo;
    }

    public void setPayBillNo(String payBillNo) {
        this.payBillNo = payBillNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(double remitAmount) {
        this.remitAmount = remitAmount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Double getYajin() {
        return yajin;
    }

    public void setYajin(Double yajin) {
        this.yajin = yajin;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    @Override
    public String toString() {
        return "RemitBean{" +
                "payBillNo='" + payBillNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", amount=" + amount +
                ", remitAmount=" + remitAmount +
                ", fee=" + fee +
                ", yajin=" + yajin +
                ", transTime=" + transTime +
                '}';
    }
}
