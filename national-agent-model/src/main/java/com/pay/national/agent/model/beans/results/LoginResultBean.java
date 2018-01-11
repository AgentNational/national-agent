package com.pay.national.agent.model.beans.results;

import java.io.Serializable;

/**
 * @Description: 登录返回消息bean
 * @see: 需要参考的类
 * @version 2017年9月8日 上午9:03:39
 * @author zhenhui.liu
 */
public class LoginResultBean implements Serializable{
	
	private static final long serialVersionUID = -2376798080152328295L;
	
	private String loginKey;//登录标示

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	@Override
	public String toString() {
		return "LoginResultBean [loginKey=" + loginKey + "]";
	}
	
	
}
