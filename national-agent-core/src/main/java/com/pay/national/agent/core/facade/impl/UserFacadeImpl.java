package com.pay.national.agent.core.facade.impl;

import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userFacade")
public class UserFacadeImpl implements UserFacade{

	@Override
	public String sendRegisterCheckCode(Map<String, String> params) {
		return null;
	}

	@Override
	public String login(LoginParamBean loginParamBean) {
		return null;
	}

	@Override
	public String logout(String loginKey) {
		return null;
	}

	@Override
	public String sendFindPasswordCheckCode(Map<String, String> params) {
		return null;
	}

	@Override
	public String findPassword(Map<String, String> params) {
		return null;
	}

	@Override
	public String resetPassword(Map<String, String> params) {
		return null;
	}

	@Override
	public String findInformation(Map<String, String> params) {
		return null;
	}
}
