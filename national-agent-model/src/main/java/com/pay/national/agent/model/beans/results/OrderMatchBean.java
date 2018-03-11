package com.pay.national.agent.model.beans.results;

/**
 * 匹配成功的订单
 * Created by shuyan.qi on 2018/3/4.
 */
public class OrderMatchBean {
    private Long orderId;
    /**
     * 用户编号
     */
    private String userNo;

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
     * 信用卡用户Id
     */
    private Long creditCardUserId;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setParentBusinessCode(String parentBusinessCode) {
        this.parentBusinessCode = parentBusinessCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreditCardUserId(Long creditCardUserId) {
        this.creditCardUserId = creditCardUserId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getUserNo() {
        return userNo;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getParentBusinessCode() {
        return parentBusinessCode;
    }

    public String getStatus() {
        return status;
    }

    public Long getCreditCardUserId() {
        return creditCardUserId;
    }

    @Override
    public String toString() {
        return "OrderMatchBean{" +
                "orderId=" + orderId +
                ", userNo='" + userNo + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", parentBusinessCode='" + parentBusinessCode + '\'' +
                ", status='" + status + '\'' +
                ", creditCardUserId=" + creditCardUserId +
                '}';
    }
}
