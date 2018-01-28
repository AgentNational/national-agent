package com.pay.national.agent.core.bean.result;

import java.io.Serializable;

/**
 * 奖励月汇总
 * @author shuyan.qi
 * @date 2018/1/25
 */
public class MonthRewardGatherBean extends BaseResultBean{
    private static final long serialVersionUID = -4867155137924084515L;
    private String month;
    private Double amount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString()+"MonthRewardGatherBean{" +
                "month='" + month + '\'' +
                ", amount=" + amount +
                '}';
    }
}
