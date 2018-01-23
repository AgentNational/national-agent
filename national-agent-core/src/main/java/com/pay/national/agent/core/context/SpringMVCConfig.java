package com.pay.national.agent.core.context;

import com.pay.commons.web.springmvc.handler.SimpleForwardHttpRequestHandler;
import com.pay.national.agent.core.interceptor.CommonHttpRequestInterceptor;
import com.pay.national.agent.core.interceptor.LoginStatusInterceptor;
import com.pay.national.agent.core.interceptor.Oauth2AccessTokenInterceptor;
import com.pay.national.agent.core.interceptor.ValidateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description: springmvc上下文配置
 * @see: 需要参考的类
 * @version 2017年9月5日 上午9:25:46
 * @author zhenhui.liu
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.pay.national.agent.core.web", useDefaultFilters = false, includeFilters = @Filter(Controller.class) )
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	/**
	 * @Description
	 * @return
	 * @see
	 */
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = new org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter();

		List<MediaType> mediaList = new ArrayList<MediaType>();
		mediaList.add(new MediaType("application", "json", Charset.forName("UTF-8")));
		mediaList.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
		mediaList.add(new MediaType("text", "html", Charset.forName("UTF-8")));
		mediaList.add(new MediaType("text", "xml", Charset.forName("UTF-8")));

		StringHttpMessageConverter stringHttpMessageConverter = new org.springframework.http.converter.StringHttpMessageConverter();
		stringHttpMessageConverter.setSupportedMediaTypes(mediaList);

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new org.springframework.http.converter.json.MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaList);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(stringHttpMessageConverter);
		messageConverters.add(mappingJackson2HttpMessageConverter);

		adapter.setMessageConverters(messageConverters);
		return adapter;
	}

	/**
	 * 配置springmvc静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/images/**").addResourceLocations("/images/**");
	}

	/**
	 * @Description 通用异常跳转页面
	 * @return
	 * @see
	 */
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		exceptionResolver.setDefaultErrorView("error/error");
		return exceptionResolver;
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(simpleMappingExceptionResolver());
		super.configureHandlerExceptionResolvers(exceptionResolvers);
	}

	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.put("/**", simpleForwardHttpRequestHandler());
		simpleUrlHandlerMapping.setMappings(mappings);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public SimpleForwardHttpRequestHandler simpleForwardHttpRequestHandler() {
		return new SimpleForwardHttpRequestHandler();
	}



	@Bean
	public Oauth2AccessTokenInterceptor oAuth2AccessTokenInterceptor(){
		return  new Oauth2AccessTokenInterceptor();
	}

	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//微信登录授权拦截器
		registry.addInterceptor(oAuth2AccessTokenInterceptor()).addPathPatterns("/**");
		// http前置请求拦截器
		registry.addInterceptor(new CommonHttpRequestInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/file/**")
				.excludePathPatterns("/web/**")
				.excludePathPatterns("/weixinEvent/**")
				.excludePathPatterns("/wxMenu/**")
				.excludePathPatterns("/weiXin/**");
		// 公共参数拦截器
		registry.addInterceptor(new ValidateInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/web/**")
				.excludePathPatterns("/wxMenu/**");
		//登录状态校验拦截器
		registry.addInterceptor(new LoginStatusInterceptor()).addPathPatterns("/**")
		.excludePathPatterns("/web/**")
		.excludePathPatterns("/file/**")
		.excludePathPatterns("/user/register")
		.excludePathPatterns("/user/login")
		.excludePathPatterns("/user/sendCheckCode")
		.excludePathPatterns("/user/findPassword")
		.excludePathPatterns("/appMenu/findAllMenu")
		.excludePathPatterns("/user/resetPassword")
		.excludePathPatterns("/creditCard/businessList")
		.excludePathPatterns("/creditCard/transact")
		.excludePathPatterns("/appAdvertiseInfo/getAppAdVertiseInfo")
		.excludePathPatterns("/appVersion/versionCheck")
		.excludePathPatterns("/weixinEvent/**")
		.excludePathPatterns("/wxMenu/**")
		.excludePathPatterns("/weiXin/**");


		super.addInterceptors(registry);
	}

	/**
	 * @Description springmvc上传文件相关
	 * @return
	 * @see
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {

		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		// commonsMultipartResolver.setMaxUploadSize(30*1024*1024);
		return commonsMultipartResolver;
	}


}
