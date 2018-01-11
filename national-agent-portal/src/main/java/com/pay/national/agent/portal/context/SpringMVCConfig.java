package com.pay.national.agent.portal.context;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.pay.commons.web.springmvc.handler.SimpleForwardHttpRequestHandlerForToHTM;
import com.pay.national.agent.common.interceptor.LoginPermissionInterceptor;

/**
 * @Description: springmvc上下文环境配置
 * @see: 需要参考的类
 * @version 2017年9月5日 下午5:57:01
 * @author zhenhui.liu
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.pay.national.agent.portal.web", useDefaultFilters = false, includeFilters = @Filter(Controller.class))
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginPermissionInterceptor("/common/permit.action?type=A"))
		.addPathPatterns("/**")
		.excludePathPatterns("/common/permit.action*");
		super.addInterceptors(registry);
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

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
		mappings.put("/**", simpleForwardHttpRequestHandlerForToHTM());
		simpleUrlHandlerMapping.setMappings(mappings);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public SimpleForwardHttpRequestHandlerForToHTM simpleForwardHttpRequestHandlerForToHTM() {
		return new SimpleForwardHttpRequestHandlerForToHTM();
	}

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {

		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		//commonsMultipartResolver.setMaxUploadSize(30*1024*1024);
		return commonsMultipartResolver;
	}

}
