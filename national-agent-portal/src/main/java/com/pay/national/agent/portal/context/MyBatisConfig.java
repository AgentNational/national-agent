package com.pay.national.agent.portal.context;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: mybatis配置
 * @see: 需要参考的类
 * @version 2017年9月5日 下午5:31:53
 * @author zhenhui.liu
 */
@Configuration
@Import(MariaDBConfig.class)
@MapperScan(sqlSessionFactoryRef = "sqlSessionFactory", basePackages = "com.pay.national.agent.portal.dao")
@EnableTransactionManagement(order = 0)
public class MyBatisConfig {
	@Resource
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return bean.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
