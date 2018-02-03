package com.pay.national.agent.model.beans.query;

/**
 *出款参数
 * @author shuyan.qi
 * @date 2018/1/30
 */
public class RemitParam {
    /**
     * 用户提现操作时的ip
     */
    private String userIp;
    /**
     *提现用户对应微信用户的openId
     */
    private String openId;
    /**
     *提现用户
     */
    private String userNo;
    /**
     *提现账户
     */
    private String accountNo;
    /**
     *提现金额
     */
    private Double amount;


    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

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
                "userIp='" + userIp + '\'' +
                ", openId='" + openId + '\'' +
                ", userNo='" + userNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
