package com.pay.national.agent.common.bean;

import java.io.Serializable;
import java.util.Date;

import com.pay.national.agent.common.annotation.ModelProp;

public class CreditCardUserModel extends ImportModel implements Serializable{
	private static final long serialVersionUID = -8771962812259443287L;
    private Integer id;
    private Integer optimistic;
    @ModelProp(name = "姓名", colIndex = 0, nullable = false)
    private String userName;
    @ModelProp(name = "电话", colIndex = 1, nullable = false)
    private String phoneNo;
    private String cardType;
    private String status;
    private Date createTime;
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

	@Override
	public String toString() {
		return "CreditCardUserModel [id=" + id + ", optimistic=" + optimistic + ", userName=" + userName + ", phoneNo="
				+ phoneNo + ", cardType=" + cardType + ", status=" + status + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
