package com.pay.national.agent.model.beans.results;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 出款详情bean
 * Created by shuyan.qi on 2018/2/7.
 */
public class RemitDetailBean {
    //提现金额
    private double amount;
    //付款单号
    private String billNo;
    //实际出款金额
    private double remitAmount;
    //出款状态
    private String remitStatus;
    //提现申请时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    //到账时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date remitTime;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public double getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(double remitAmount) {
        this.remitAmount = remitAmount;
    }

    public String getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(String remitStatus) {
        this.remitStatus = remitStatus;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getRemitTime() {
        return remitTime;
    }

    public void setRemitTime(Date remitTime) {
        this.remitTime = remitTime;
    }

    @Override
    public String toString() {
        return "RemitDetailBean{" +
                "amount=" + amount +
                ", billNo='" + billNo + '\'' +
                ", remitAmount=" + remitAmount +
                ", remitStatus='" + remitStatus + '\'' +
                ", applyTime=" + applyTime +
                ", remitTime=" + remitTime +
                '}';
    }
}
