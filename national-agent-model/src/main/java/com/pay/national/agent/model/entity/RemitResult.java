package com.pay.national.agent.model.entity;

import java.util.Date;

public class RemitResult {
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
     * 付款请求ID
     *
     * @mbggenerated
     */
    private String remitRequestId;

    /**
     * 付款单ID
     *
     * @mbggenerated
     */
    private String remitPaymentId;

    /**
     * 付款状态
     *
     * @mbggenerated
     */
    private String remitStatus;

    /**
     * 处理状态
     *
     * @mbggenerated
     */
    private String processStatus;

    /**
     * 处理时间
     *
     * @mbggenerated
     */
    private Date processTime;

    /**
     * 结算单原状态
     *
     * @mbggenerated
     */
    private String paymentOldStatus;

    /**
     * 结算单新状态
     *
     * @mbggenerated
     */
    private String paymentNewStatus;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 变更时间
     *
     * @mbggenerated
     */
    private Date changeTime;

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

    public String getRemitPaymentId() {
        return remitPaymentId;
    }

    public void setRemitPaymentId(String remitPaymentId) {
        this.remitPaymentId = remitPaymentId;
    }

    public String getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(String remitStatus) {
        this.remitStatus = remitStatus;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getPaymentOldStatus() {
        return paymentOldStatus;
    }

    public void setPaymentOldStatus(String paymentOldStatus) {
        this.paymentOldStatus = paymentOldStatus;
    }

    public String getPaymentNewStatus() {
        return paymentNewStatus;
    }

    public void setPaymentNewStatus(String paymentNewStatus) {
        this.paymentNewStatus = paymentNewStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	@Override
	public String toString() {
		return "RemitResult [id=" + id + ", optimistic=" + optimistic + ", remitRequestId=" + remitRequestId
				+ ", remitPaymentId=" + remitPaymentId + ", remitStatus=" + remitStatus + ", processStatus="
				+ processStatus + ", processTime=" + processTime + ", paymentOldStatus=" + paymentOldStatus
				+ ", paymentNewStatus=" + paymentNewStatus + ", remark=" + remark + ", createTime=" + createTime
				+ ", changeTime=" + changeTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
    
    
}