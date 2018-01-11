package com.pay.national.agent.model.entity;

import java.util.Date;

public class UserLoginLog {
    /**
     * id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 用户登录名
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 用户编号
     *
     * @mbggenerated
     */
    private String loginKey;

    /**
     * 设备号
     *
     * @mbggenerated
     */
    private String deviceNo;

    /**
     * 设备类型
     *
     * @mbggenerated
     */
    private String deviceType;

    /**
     * ip地址
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * 经纬度
     *
     * @mbggenerated
     */
    private String coordinate;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 可用状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 系统类型
     *
     * @mbggenerated
     */
    private String osType;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

	@Override
	public String toString() {
		return "UserLoginLog [id=" + id + ", optimistic=" + optimistic + ", userName=" + userName + ", userNo=" + userNo
				+ ", loginKey=" + loginKey + ", deviceNo=" + deviceNo + ", deviceType=" + deviceType + ", ip=" + ip
				+ ", coordinate=" + coordinate + ", createTime=" + createTime + ", status=" + status + ", osType="
				+ osType + ", version=" + version + "]";
	}
}