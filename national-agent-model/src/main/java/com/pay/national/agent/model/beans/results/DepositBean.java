package com.pay.national.agent.model.beans.results;

/**
 *入账返回bean
 * @author shuyan.qi
 * @date 2018/1/30
 */
public class DepositBean{
    /**
     * 入账结果 SUCCESS/FAIL
     */
    private String result;
    /**
     * 备注信息
     */
    private String msg;
    /**
     * 账户记录id
     */
    private String accountHistoryId;

    public void setResult(String result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setAccountHistoryId(String accountHistoryId) {
        this.accountHistoryId = accountHistoryId;
    }

    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public String getAccountHistoryId() {
        return accountHistoryId;
    }

    @Override
    public String toString() {
        return "DepositBean{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", accountHistoryId='" + accountHistoryId + '\'' +
                '}';
    }
}
