package com.pay.national.agent.core.context;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pay.national.agent.common.utils.LogUtil;

/**
 * @Description: 数据库链接配置
 * @see: 需要参考的类
 * @version 2017年9月4日 下午5:32:46
 * @author zhenhui.liu
 */
@Configuration
public class MariaDBConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动  
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/mysql");//设置连接数据库的URL  
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
