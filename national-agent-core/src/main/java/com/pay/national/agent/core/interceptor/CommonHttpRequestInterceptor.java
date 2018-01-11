package com.pay.national.agent.core.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.constants.CiphersConstant;
import com.pay.national.agent.common.utils.CommUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;


/**
 * @Description: 前置http请求拦截器
 * @see: CommonHttpRequestInterceptor 此处填写需要参考的类
 * @version 2016年8月29日 上午10:40:09
 * @author qinji.xu
 */
public class CommonHttpRequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(CommonHttpRequestInterceptor.class);

	/**
	 * 验证数字签名
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String key = CiphersConstant.MD5_SIGN_KEY;

		Map<String, Object> requestParam = getRequestMap(request);

		// 用户版本号
		String appVersion = request.getHeader("appVersion");
		if (StringUtils.isBlank(appVersion)) {
			// 从请求参数中获取
			appVersion = request.getParameter("appVersion");
		}
		//设备来源
		String deviceSource  = request.getParameter("deviceSource");
		logger.info("appVersion : {} ,deviceSource : {}",appVersion,deviceSource);
		String url = request.getRequestURL().toString();
		//人脸识别图片比对接口  base64中若包含加号,传到后台会变为空格,此处将空格转为加号
		if(url.contains("faceRecoPairVerify")){
			String query_image_package = requestParam.get("query_image_package") == null ? "" : (String)requestParam.get("query_image_package");
			String database_image_content = requestParam.get("database_image_content") == null ? "" : (String)requestParam.get("database_image_content");
			if(org.springframework.util.StringUtils.hasText(query_image_package)){
				requestParam.put("query_image_package",query_image_package.replace(' ', '+'));
			}
			if(org.springframework.util.StringUtils.hasText(database_image_content)){
				requestParam.put("database_image_content",database_image_content.replace(' ', '+'));
			}
			
		}
		String dataJson = "";
		dataJson = CommUtils.sortRequestParam(requestParam,true);
		String sign = request.getParameter("sign");
		// base64资质文件上传
		boolean fileControl = url.contains("uploadQualificationImg") || url.contains("faceRecoPairVerify");
		logger.info("interceptor CommonHttpRequestInterceptor requestUrl：url = {},requestParams ：dataJson = {}, sign = {}", url,fileControl?"base64文件上传不展示": dataJson,
				request.getParameter("sign"));

		if (!CommUtils.checkHmac(dataJson, sign, key)) {
			logger.info("interceptor CommonHttpRequestInterceptor url = {}, data = {}, sign = {}, dataSign failed", url, fileControl?"base64文件上传不展示": dataJson, sign);
			ReturnBean<Object> returnBean = new ReturnBean<Object>();
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("签名验证失败");
			response.getWriter().print(JSONUtils.alibabaJsonString(returnBean));
			setCrosResponse(response);
			return false;
		}

		logger.info("interceptor CommonHttpRequestInterceptor url = {}, data = {}, sign = {}, dataSign success", url, fileControl?"base64文件上传不展示": dataJson, sign);
		return true;
	}

	/**
	 * 组装request参数为map
	 */
	protected Map<String, Object> getRequestMap(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();

		for (Enumeration<String> e = paramNames; e.hasMoreElements();) {
			String thisName = e.nextElement().toString();
			String thisValue = request.getParameter(thisName);

			if("sign".equals(thisName)) {
				continue;
			} else {
				paramMap.put(thisName, thisValue);
			}
		}
		return paramMap;
	}
	private void setCrosResponse(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET");
	}
}
