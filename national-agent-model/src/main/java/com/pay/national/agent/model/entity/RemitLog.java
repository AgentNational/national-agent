package com.pay.national.agent.model.entity;

import java.util.Date;

public class RemitLog {
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
     * 用户编号
     */
    private String userNo;
    
    /**
     * 付款金额
     */
    private Double amount;

    /**
     * 付款请求ID
     *
     * @mbggenerated
     */
    private String remitRequestId;

    /**
     * 开始执行时间
     *
     * @mbggenerated
     */
    private Date beginExecuteTime;

    /**
     * 结束执行时间
     *
     * @mbggenerated
     */
    private Date endExecuteTime;

    /**
     * 执行结果
     *
     * @mbggenerated
     */
    private String executeResult;

    /**
     * 错误编码
     *
     * @mbggenerated
     */
    private String errorCode;

    /**
     * 错误信息
     *
     * @mbggenerated
     */
    private String errorMsg;

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

    public String getRemitRequestId() {
        return remitRequestId;
    }

    public void setRemitRequestId(String remitRequestId) {
        this.remitRequestId = remitRequestId;
    }

    public Date getBeginExecuteTime() {
        return beginExecuteTime;
    }

    public void setBeginExecuteTime(Date beginExecuteTime) {
        this.beginExecuteTime = beginExecuteTime;
    }

    public Date getEndExecuteTime() {
        return endExecuteTime;
    }

    public void setEndExecuteTime(Date endExecuteTime) {
        this.endExecuteTime = endExecuteTime;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
		return "RemitLog [id=" + id + ", optimistic=" + optimistic + ", userNo=" + userNo + ", amount=" + amount
				+ ", remitRequestId=" + remitRequestId + ", beginExecuteTime=" + beginExecuteTime + ", endExecuteTime="
				+ endExecuteTime + ", executeResult=" + executeResult + ", errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}