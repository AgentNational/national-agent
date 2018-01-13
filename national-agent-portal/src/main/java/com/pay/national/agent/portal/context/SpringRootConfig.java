package com.pay.national.agent.portal.context;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Description: spring上下文配置
 * @see: 需要参考的类
 * @version 2017年9月5日 下午5:56:42
 * @author zhenhui.liu
 */
@Configuration
@EnableAspectJAutoProxy
@Import({MariaDBConfig.class,MyBatisConfig.class})
@ComponentScan(basePackages = "com.pay.national.agent.portal", excludeFilters = { @Filter(Controller.class), @Filter(Configuration.class) })
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
