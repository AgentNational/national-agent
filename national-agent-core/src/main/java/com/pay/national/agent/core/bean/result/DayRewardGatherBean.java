package com.pay.national.agent.core.bean.result;

/**
 * 奖励日汇总
 *
 * @author shuyan.qi
 * @date 2018/1/26
 */
public class DayRewardGatherBean extends BaseResultBean{
    private static final long serialVersionUID = 1189242329221165237L;
    private String day;
    private Double amount;

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

    @Override
    public String toString() {
        return super.toString()+"DayRewardGatherBean{" +
                "day='" + day + '\'' +
                ", amount=" + amount +
                '}';
    }
}
