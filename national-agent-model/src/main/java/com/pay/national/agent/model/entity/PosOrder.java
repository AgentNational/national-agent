package com.pay.national.agent.model.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.PosOrderStatus;
import com.pay.national.agent.model.enums.PosStatus;

public class PosOrder {
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
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * pos类型
     *
     * @mbggenerated
     */
    private String posType;

    /**
     * 业务编码
     *
     * @mbggenerated
     */
    private ChildBusinessCode businessCode;

    /**
     * 总奖励金额
     *
     * @mbggenerated
     */
    private Double rewardAmountTotal;

    /**
     * 已奖励金额
     *
     * @mbggenerated
     */
    private Double rewardAmount;

    /**
     * 商户编号
     *
     * @mbggenerated
     */
    private String customerNo;

    /**
     * 商户名称
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * 采购单号
     *
     * @mbggenerated
     */
    private String salesBillNo;

    /**
     * 出库单号
     *
     * @mbggenerated
     */
    private String storeOutNo;

    /**
     * pos序列号
     *
     * @mbggenerated
     */
    private String posSn;

    /**
     * 交易总金额
     *
     * @mbggenerated
     */
    private Double dealTotal;

    /**
     * pos状态 未发货/已发货/已绑定
     *
     * @mbggenerated
     */
    private PosStatus posStatus;

    /**
     * 状态 未开始/已开始/已奖励完毕
     *
     * @mbggenerated
     */
    private PosOrderStatus status;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

    /**
     * 收货人手机号
     *
     * @mbggenerated
     */
    private String customerPhone;

    /**
     * 收货人地址
     *
     * @mbggenerated
     */
    private String receiveAddress;

    /**
     * 收货人
     *
     * @mbggenerated
     */
    private String linkman;

    /**
     * 失效时间
     *
     * @mbggenerated
     */
    @JSONField(format="yyyy.MM.dd")
    private Date invalidTime;

    /**
     * 最后更新日期
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }


    public ChildBusinessCode getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(ChildBusinessCode businessCode) {
		this.businessCode = businessCode;
	}

	public Double getRewardAmountTotal() {
        return rewardAmountTotal;
    }

    public void setRewardAmountTotal(Double rewardAmountTotal) {
        this.rewardAmountTotal = rewardAmountTotal;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesBillNo() {
        return salesBillNo;
    }

    public void setSalesBillNo(String salesBillNo) {
        this.salesBillNo = salesBillNo;
    }

    public String getStoreOutNo() {
        return storeOutNo;
    }

    public void setStoreOutNo(String storeOutNo) {
        this.storeOutNo = storeOutNo;
    }

    public String getPosSn() {
        return posSn;
    }

    public void setPosSn(String posSn) {
        this.posSn = posSn;
    }

    public Double getDealTotal() {
        return dealTotal;
    }

    public void setDealTotal(Double dealTotal) {
        this.dealTotal = dealTotal;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public PosStatus getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(PosStatus posStatus) {
		this.posStatus = posStatus;
	}

	public PosOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PosOrderStatus status) {
		this.status = status;
	}

	public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}