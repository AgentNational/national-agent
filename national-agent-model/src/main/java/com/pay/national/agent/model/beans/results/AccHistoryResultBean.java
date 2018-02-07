package com.pay.national.agent.model.beans.results;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 *
 * @author shuyan.qi
 * @date 2018/2/6
 */
public class AccHistoryResultBean {

    /**
     * 金额
     */
    private Double amount;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     *业务编码
     */
    private String businessCode;

    /**
     *父业务编码
     */
    private String parentBusinessCode;

    /**
     *状态
     */
    private String status;

    /**
     *操作(加plus、减sub)
     */
    private String symbol;

    /**
     *创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    /**
     *最后更新时间
     */
    private Date lastUpdateTime;

    /**
     *微信付款单号
     */
    private String wxBillNo;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getParentBusinessCode() {
        return parentBusinessCode;
    }

    public void setParentBusinessCode(String parentBusinessCode) {
        this.parentBusinessCode = parentBusinessCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getWxBillNo() {
        return wxBillNo;
    }

    public void setWxBillNo(String wxBillNo) {
        this.wxBillNo = wxBillNo;
    }

    @Override
    public String toString() {
        return "AccHistoryResultBean{" +
                "amount=" + amount +
                ", businessName='" + businessName + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", status='" + status + '\'' +
                ", symbol='" + symbol + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", wxBillNo='" + wxBillNo + '\'' +
                '}';
    }
}
