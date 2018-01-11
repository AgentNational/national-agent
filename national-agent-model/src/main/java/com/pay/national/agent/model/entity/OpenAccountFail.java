package com.pay.national.agent.model.entity;

import java.util.Date;

public class OpenAccountFail {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 状态
     */
    private String status;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
	public String toString() {
		return "OpenAccountFail [id=" + id + ", userNo=" + userNo + ", createTime=" + createTime + ", status=" + status
				+ "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}