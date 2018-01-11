package com.pay.national.agent.core.context;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: MyBatis配置
 * @see: MyBatisConfig 此处填写需要参考的类
 * @version 2016年8月24日 下午6:23:57
 * @author qinji.xu
 */
@Configuration
@MapperScan("com.pay.national.agent.core.dao")
@EnableTransactionManagement(order = 0)
public class MyBatisConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);	
	
	@javax.annotation.Resource
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		try {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			Resource configLocation = new org.springframework.core.io.ClassPathResource("mybatis-config.xml");
			bean.setConfigLocation(configLocation);
			return bean.getObject();
		} catch (Exception e) {
			logger.error("create sqlSessionFactory error ", e);
			return null;
		}
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
