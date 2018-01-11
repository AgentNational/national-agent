package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

/**
 * @Description: 注册参数bean
 * @see: 需要参考的类
 * @version 2017年9月6日 下午4:14:12
 * @author zhenhui.liu
 */
public class RegisterParamBean implements Serializable{

	private static final long serialVersionUID = 9136197147102495887L;

	private String phoneNo;//注册手机号
	
	private String password;//密码
	
	private String repeatPassword;//重复密码
	
	private String parentUserNo;//推荐人用户编码
	
	private String checkCode;//验证码
	
	private String deviceNo;//设备号
	
	private String osType;//操作系统
	
	private String version;//版本号
	
	private String deviceType;//系统类型

	private String source;//注册来源
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getParentUserNo() {
		return parentUserNo;
	}

	public void setParentUserNo(String parentUserNo) {
		this.parentUserNo = parentUserNo;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Override
	public String toString() {
		return "RegisterParamBean [phoneNo=" + phoneNo + ", password=" + password + ", repeatPassword=" + repeatPassword
				+ ", parentUserNo=" + parentUserNo + ", checkCode=" + checkCode + ", deviceNo=" + deviceNo + ", osType="
				+ osType + ", version=" + version + ", deviceType=" + deviceType + ", source=" + source + "]";
	}
	
	
	
	
}
