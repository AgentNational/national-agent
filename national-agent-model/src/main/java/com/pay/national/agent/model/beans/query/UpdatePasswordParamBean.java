package com.pay.national.agent.model.beans.query;

import java.io.Serializable;

/**
 * @Description: 用户修改密码参数bean
 * @see: 需要参考的类
 * @version 2017年9月8日 下午4:44:15
 * @author zhenhui.liu
 */
public class UpdatePasswordParamBean implements Serializable{

	private static final long serialVersionUID = -770577644084305675L;
	
	private String loginKey;//登录唯一标示
	
	private String password;//原密码
	
	private String newPassword;//新密码

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UpdatePasswordParamBean [loginKey=" + loginKey + ", password=" + password + ", newPassword="
				+ newPassword + "]";
	}
	
	
	

}
