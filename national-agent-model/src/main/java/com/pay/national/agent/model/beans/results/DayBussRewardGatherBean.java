package com.pay.national.agent.model.beans.results;

import java.io.Serializable;

public class DayBussRewardGatherBean implements Serializable{
    private static final long serialVersionUID = -5053903193077633042L;

    private String day;//时间

    private String nickName;//昵称

    private Double amount;//金额

    private Double totalAmount;//总金额

    private String lowerUserNo;//下级编号

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLowerUserNo() {
        return lowerUserNo;
    }

    public void setLowerUserNo(String lowerUserNo) {
        this.lowerUserNo = lowerUserNo;
    }

    @Override
    public String toString() {
        return "DayBussRewardGatherBean{" +
                "day='" + day + '\'' +
                ", nickName='" + nickName + '\'' +
                ", amount=" + amount +
                ", totalAmount=" + totalAmount +
                ", lowerUserNo='" + lowerUserNo + '\'' +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
