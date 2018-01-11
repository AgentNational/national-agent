package com.pay.national.agent.portal.context;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pay.dsmclient.v2.c3p0.C3p0PooledDataSource;

/**
 * @Description: 数据源配置
 * @see: 需要参考的类
 * @version 2017年9月5日 下午5:31:36
 * @author zhenhui.liu
 */
@Configuration
@PropertySource("classpath:/system.properties")
public class MariaDBConfig {

	private static final Logger logger = LoggerFactory.getLogger(MariaDBConfig.class);
	
	@Value("${com.pay.national.agent.portal.ds.name}")
	private String dsName;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		long l = System.currentTimeMillis();
		logger.info("marDs = {} , {}",dsName,l);
		C3p0PooledDataSource ds = new C3p0PooledDataSource();
		ds.setDataSourceName(dsName);
		return ds;
	}

}
