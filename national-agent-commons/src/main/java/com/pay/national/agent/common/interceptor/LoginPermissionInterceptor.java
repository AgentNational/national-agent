/**
 *
 */
package com.pay.national.agent.common.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.DateFormatUtils;
import com.pay.commons.utils.lang.JsonUtils;
import com.pay.commons.web.WebConstants;
import com.pay.commons.web.springmvc.annotation.PermissionCheck;
import com.pay.commons.web.springmvc.annotation.PermissionCheckType;
import com.pay.national.agent.common.bean.Authorization;

/**
 * @Description: 单点登录通用参数转换拦截器
 * @see: 需要参考的类
 * @version 2017年9月4日 下午4:16:28
 * @author zhenhui.liu
 */
public class LoginPermissionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginPermissionInterceptor.class);
	private static final Integer REDIS_DB_INDEX = 1;
	private String redirectPath;

	private static final String PLOUTO_OPERATOR = "plouto.operator";
	private static final String SESSION_AUTH = "plouto.auth";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String className = handlerMethod.getMethod().getDeclaringClass().getName();
			String methodName = handlerMethod.getMethod().getName();
			PermissionCheck methodAnno = handlerMethod.getMethodAnnotation(PermissionCheck.class);
			PermissionCheck classAnno = handlerMethod.getMethod().getDeclaringClass().getAnnotation(PermissionCheck.class);

			String operator = (String) request.getSession().getAttribute(WebConstants.SESSION_OPERATOR);

			if ("".equals(operator) || null == operator) {
				Object object = request.getSession().getAttribute("_const_cas_assertion_");
				if (object == null) {
					logger.error("Don't has operator");
					return false;
				}
				Assertion assertion = (Assertion) object;
				operator = assertion.getPrincipal().getName();
				request.getSession().setAttribute(WebConstants.SESSION_OPERATOR, operator);
			}

			this.forAuth(request, operator);

			boolean hasPermission = false;
			if ((methodAnno == null || (Arrays.asList(methodAnno.checkType()).contains(PermissionCheckType.LOGIN) && methodAnno.value()))
					|| (classAnno == null || (Arrays.asList(classAnno.checkType()).contains(PermissionCheckType.LOGIN) && classAnno.value()))) {
				@SuppressWarnings("unchecked")
				Set<String> set = CacheUtils.get(REDIS_DB_INDEX, WebConstants.BOSS_OPERATOR_RESOURCE + "." + operator, Set.class, true);
				String servletPath = request.getServletPath();
				String resource = servletPath.replaceFirst("/", "");
				String resource1 = servletPath.substring(servletPath.lastIndexOf("/")+1);
				logger.info("resource = {} , resource1 = {}",resource,resource1);
				String requestUrl = request.getRequestURL().toString();
				String remoteAddress = requestUrl.substring(0, requestUrl.indexOf(servletPath));
				if (!set.contains(resource1)) {
					logger.info("noPermit! for operator {} action={}", operator, resource1);
					String url = remoteAddress + redirectPath;
					response.sendRedirect(url);
					return hasPermission;
				}
				hasPermission = true;
			}

			logger.info("{}.{} request by {} at {} {} has permission", className, methodName, operator, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
					hasPermission ? "" : "don't");
			return hasPermission;
		}
		return true;
	}

	private void forAuth(HttpServletRequest request, String operator) {
		Authorization auth  = (Authorization) request.getSession().getAttribute(SESSION_AUTH);
		if(null == auth)
		{
			@SuppressWarnings("unchecked")
			Map<String,Object> authMap = CacheUtils.get(REDIS_DB_INDEX, PLOUTO_OPERATOR + "." + operator, Map.class, true);
			if(null != authMap)
			{
				auth = JsonUtils.toJsonToObject(authMap, Authorization.class);
			}
			else
			{
				auth = new Authorization();
				auth.setUserName(operator);
			}
			logger.info("plouto.auth :{}" + auth);
			request.getSession().setAttribute(SESSION_AUTH, auth);
		}
	}

	public LoginPermissionInterceptor(String redirectPath) {
		this.redirectPath = redirectPath;
	}
}
