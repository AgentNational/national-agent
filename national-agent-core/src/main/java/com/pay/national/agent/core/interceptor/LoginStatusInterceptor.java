/**
 *
 */
package com.pay.national.agent.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.Constants;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.User;

/**
 * 登录验证拦截器
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: LoginStatusInterceptor 此处填写需要参考的类
 * @version 2015年12月25日 下午2:19:37
 * @author dong.lian
 */
public class LoginStatusInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginStatusInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 用户登录时分配的token
		String loginKey = request.getHeader(Constants.TOKEN);
		if (StringUtils.isBlank(loginKey)) {
			// 从请求参数中获取
			loginKey = request.getParameter(Constants.TOKEN);
		}
		logger.info("interceptor LoginStatusInterceptor loginKey = {}", loginKey);
		if (StringUtils.isBlank(loginKey)) {
			response.getWriter().write(JSONUtils.alibabaJsonString(new ReturnBean<Object>(RetCodeConstants.LOGIN_FAIL,RetCodeConstants.LOGIN_FAIL_DESC)));
			return false;
		}
		//判断loginKey是否有效
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
		if(user == null){
			response.getWriter().write(JSONUtils.alibabaJsonString(new ReturnBean<Object>(RetCodeConstants.LOGIN_FAIL,RetCodeConstants.LOGIN_FAIL_DESC)));
			setCrosResponse(response);
			return false;
		}else{
			logger.info("interceptor LoginStatusInterceptor loginKey = {},userNo:{}", loginKey,user.getUserNo());
		}
		logger.info("interceptor LoginStatusInterceptor token = {},dataSign success ", loginKey);
		return true;
	}
	private void setCrosResponse(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET");
	}
}
