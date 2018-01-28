package com.pay.national.agent.core.bean.result;

import java.io.Serializable;
import java.util.List;

/**
 * 奖励汇总
 *
 * @author shuyan.qi
 * @date 2018/1/25
 */
public class RewardGatherBean extends BaseResultBean {
    private static final long serialVersionUID = 4292752653719925137L;
    /**
     * 奖励总金额
     */
    private Double totalAmount;

    /**
     * 奖励汇总明细
     */
    private List<RewardGatherDetailBean> details;


   public class RewardGatherDetailBean{
        /**
         * 父业务编码
         */
        private String parentBusinessCode;
        /**
         * 奖励总金额
         */
        private Double amount;

        public String getParentBusinessCode() {
            return parentBusinessCode;
        }

        public void setParentBusinessCode(String parentBusinessCode) {
            this.parentBusinessCode = parentBusinessCode;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "RewardGatherDetailBean{" +
                    "parentBusinessCode='" + parentBusinessCode + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<RewardGatherDetailBean> getDetails() {
        return details;
    }

    public void setDetails(List<RewardGatherDetailBean> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return super.toString()+"RewardGatherBean{" +
                "totalAmount=" + totalAmount +
                ", details=" + details +
                '}';
    }
}
