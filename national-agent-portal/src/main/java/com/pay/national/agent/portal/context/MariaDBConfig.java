package com.pay.national.agent.portal.context;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pay.national.agent.common.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


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

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		long l = System.currentTimeMillis();
		logger.info("marDs = {} , {}",dsName,l);
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/mysql?autoReconnect=true");//设置连接数据库的URL
		ds.setUser("root");//设置连接数据库的用户名
		ds.setPassword("admin");//设置连接数据库的密码
		ds.setMaxPoolSize(40);//设置连接池的最大连接数
		ds.setMinPoolSize(2);//设置连接池的最小连接数
		ds.setInitialPoolSize(10);//设置连接池的初始连接数
		ds.setMaxStatements(100);//设置连接池的缓存Statement的最大数
		LogUtil.info("数据库连接池已启动...");
		return ds;
	}

}
