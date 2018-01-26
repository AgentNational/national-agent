package com.pay.national.agent.model.entity;

import java.io.Serializable;

/**
 * 微信支付详细信息
 */
public class WxPublicPayDetail implements Serializable{


    /**
     *
     */
    private static final long serialVersionUID = -8913672378258960791L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 版本号
     */
    private Integer optimistic;
    /**
     * 商户订单号
     */
    private String outerTradeNo;
    /**
     * 通信标识（SUCCESS/FAIL）
     */
    private String returnCode;
    /**
     * 返回信息
     */
    private String returnMsg;
    /**
     * 业务结果（SUCCESS/FAIL）
     */
    private String resultCode;
    /**
     * 错误代码
     */
    private String errCode;
    /**
     * 错误代码描述
     */
    private String errCodeDes;
    /**
     * 业务类型(区分业务场景如:下单、异步。。。)
     */
    private String busiType;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 备注
     */
    private String remark;

    public WxPublicPayDetail() {
        super();
    }

    public WxPublicPayDetail(String outerTradeNo, String returnCode, String returnMsg, String resultCode,
                             String errCode, String errCodeDes, String busiType, String remark) {
        super();
        this.outerTradeNo = outerTradeNo;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.busiType = busiType;
        this.remark = remark;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.Integer getOptimistic() {
        return this.optimistic;
    }

    public void setOptimistic(java.lang.Integer optimistic) {
        this.optimistic = optimistic;
    }

    public java.lang.String getOuterTradeNo() {
        return this.outerTradeNo;
    }

    public void setOuterTradeNo(java.lang.String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public java.lang.String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(java.lang.String returnCode) {
        this.returnCode = returnCode;
    }

    public java.lang.String getReturnMsg() {
        return this.returnMsg;
    }

    public void setReturnMsg(java.lang.String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public java.lang.String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }

    public java.lang.String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(java.lang.String errCode) {
        this.errCode = errCode;
    }

    public java.lang.String getErrCodeDes() {
        return this.errCodeDes;
    }

    public void setErrCodeDes(java.lang.String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public java.lang.String getBusiType() {
        return this.busiType;
    }

    public void setBusiType(java.lang.String busiType) {
        this.busiType = busiType;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
