package com.pay.national.agent.core.context;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Description: Web应用初始化
 * @see: WebInitializer 此处填写需要参考的类
 * @version 2016年8月24日 下午6:30:16
 * @author qinji.xu
 */
public class WebInitializer implements WebApplicationInitializer {
	private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("WebInitializer init start");
		// 创建Spring上下文
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringRootConfig.class);
		try {
			rootContext.getEnvironment().getPropertySources().addFirst(new ResourcePropertySource("classpath:/environment.properties"));
		} catch (IOException e) {
			logger.error("", e);
		}
		// 管理Spring上下文的生命周期
		servletContext.addListener(new ContextLoaderListener(rootContext));
		// 加载代付配置信息
		//servletContext.addListener(new DpayContentLoaderListener());
		// 字符编码过滤器
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", encodingFilter);
		characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

		// 创建SpringMVC上下文
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(SpringMVCConfig.class);
		// 注册SpringMVC分发器
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		logger.info("WebInitializer init end");
	}
}
