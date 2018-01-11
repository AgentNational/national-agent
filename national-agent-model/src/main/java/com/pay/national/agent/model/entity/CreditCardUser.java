package com.pay.national.agent.model.entity;

import java.util.Date;

import com.pay.national.agent.model.enums.CreditCardUserStatus;

public class CreditCardUser {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 用户姓名
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phoneNo;

    /**
     * 信用卡类型
     *
     * @mbggenerated
     */
    private String cardType;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private CreditCardUserStatus status;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 最后更新时间
     *
     * @mbggenerated
     */
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

    public CreditCardUserStatus getStatus() {
        return status;
    }

    public void setStatus(CreditCardUserStatus status) {
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
		return "CreditCardUser [id=" + id + ", optimistic=" + optimistic + ", userName=" + userName + ", phoneNo="
				+ phoneNo + ", cardType=" + cardType + ", status=" + status + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
    
    
}