package com.pay.national.agent.core.context;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import com.pay.commons.cache.context.CacheConfig;

/**
 * @Description: spring上下文配置
 * @see: 需要参考的类
 * @version 2017年9月5日 上午9:26:05
 * @author zhenhui.liu
 */
@Configuration
@EnableAspectJAutoProxy
@Import({ MariaDBConfig.class,MyBatisConfig.class,CacheConfig.class, SMSConfig.class})
@ComponentScan(basePackages = "com.pay.national.agent.core", excludeFilters = { @Filter(Controller.class), @Filter(Configuration.class) })
public class SpringRootConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Validator validator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}


}
