package com.pay.national.agent.model.entity;

import java.io.Serializable;
/**
 * @Description: 微信付款操作详情
 */
public class WxPayDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4410150108421906110L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 版本号
     */
    private Integer optimistic;
    /**
     * 外部商户编号
     */
    private String outerAppid;
    /**
     * 外部订单号
     */
    private String outerTradeNo;
    /**
     * 外部openId
     */
    private String outerOpenid;
    /**
     * 商户编号
     */
    private String userNo;
    /**
     * 返回状态码
     */
    private String status;
    /**
     * 返回信息
     */
    private String returnMsg;
    /**
     * 操作时间
     */
    private java.util.Date createTime;
    /**
     * 备注
     */
    private String remark;

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

    public String getOuterAppid() {
        return outerAppid;
    }

    public void setOuterAppid(String outerAppid) {
        this.outerAppid = outerAppid;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getOuterOpenid() {
        return outerOpenid;
    }

    public void setOuterOpenid(String outerOpenid) {
        this.outerOpenid = outerOpenid;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getReturnMsg() {
        return this.returnMsg;
    }

    public void setReturnMsg(java.lang.String returnMsg) {
        this.returnMsg = returnMsg;
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

    public WxPayDetail() {
    }

    public WxPayDetail(String outerAppid, String outerTradeNo, String outerOpenid, String userNo, String status,
                       String returnMsg, String remark) {
        super();
        this.outerAppid = outerAppid;
        this.outerTradeNo = outerTradeNo;
        this.outerOpenid = outerOpenid;
        this.userNo = userNo;
        this.status = status;
        this.returnMsg = returnMsg;
        this.remark = remark;
    }
}
