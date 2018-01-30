package com.pay.national.agent.model.beans.query;

/**
 *出款参数
 * @author shuyan.qi
 * @date 2018/1/30
 */
public class RemitParam {
    private String userNo;
    private Double amount;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RemitParam{" +
                "userNo='" + userNo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
