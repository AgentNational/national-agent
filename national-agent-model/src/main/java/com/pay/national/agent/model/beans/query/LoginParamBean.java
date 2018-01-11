package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

/**
 * @Description: 用户登录参数bean
 * @see: 需要参考的类
 * @version 2017年9月6日 下午5:58:23
 * @author zhenhui.liu
 */
public class LoginParamBean implements Serializable{

	private static final long serialVersionUID = -7551337165013379332L;
	
	private String userName;//用户名
	
	private String password;//密码
	
	private String coordinate;//经纬度
	
	private String deviceNo;//设备号
	
	private String osType;//操作系统
	
	private String version;//版本号
	
	private String deviceType;//系统类型

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}


	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
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

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "LoginParamBean [userName=" + userName + ", password=" + password + ", coordinate=" + coordinate
				+ ", deviceNo=" + deviceNo + ", osType=" + osType + ", version=" + version + ", deviceType="
				+ deviceType + "]";
	}
	
	

}
