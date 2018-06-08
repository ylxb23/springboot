package org.zero.boot.web.init.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;

/**
 * Druid连接池配置信息,使用third库
 * @date 2017年11月3日 下午1:57:52
 * @author zero
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "datasource.druid")
@MapperScan(basePackages= {"org.zero.boot.dao.third"}, sqlSessionFactoryRef="druidSqlSessionFactory")
public class ThirdDataSourceConfig {
	private final Logger logger = LoggerFactory.getLogger(ThirdDataSourceConfig.class);
	
	private String jdbcUrl;  
	  
    private String username;  
  
    private String password;  
  
    private String jdbcClass;  
  
    private int initialSize;  
  
    private int minIdle;  
  
    private int maxActive;  
  
    private int maxWait;  
  
    private int timeBetweenEvictionRunsMillis;  
  
    private int minEvictableIdleTimeMillis;  
  
    private String validationQuery;  
    
    private int queryTimeout;
  
    private boolean testWhileIdle;  
  
    private boolean testOnBorrow;  
  
    private boolean testOnReturn;  
  
    private boolean poolPreparedStatements;  
  
    private int maxPoolPreparedStatementPerConnectionSize;  
  
    private String filters;  
  
    private String connectionProperties;
	
	@Qualifier("druidDataSource")
	@Bean(name="druidDataSource")
	public DruidDataSource druidDataSource() {
		DruidDataSource firstDruid = new DruidDataSource();
		firstDruid.setUrl(jdbcUrl);
		firstDruid.setUsername(username);
		firstDruid.setPassword(password);
		firstDruid.setDriverClassName(jdbcClass);
		// connection pool configurations
		firstDruid.setInitialSize(initialSize);
		firstDruid.setMinIdle(minIdle);
		firstDruid.setMaxActive(maxActive);
		firstDruid.setMaxWait(maxWait);
		firstDruid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		firstDruid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		firstDruid.setValidationQuery(validationQuery);
		firstDruid.setQueryTimeout(queryTimeout);
		firstDruid.setPoolPreparedStatements(poolPreparedStatements);
		firstDruid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			firstDruid.setFilters(filters);
		} catch(Exception e) {
			logger.error("druid configuration initialization filter error", e);
		}
		firstDruid.setConnectionProperties(connectionProperties);
		return firstDruid;
	}
	
	@Qualifier("druidSqlSessionFactory")
	@Bean(name="druidSqlSessionFactory")
	public SqlSessionFactory druidSqlSessionFactory(@Qualifier("druidDataSource") DataSource druidDataSource) throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(druidDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:/mapper/third/*.xml"));
		return bean.getObject();
	}
	
	@Qualifier("druidDataSourceTransactionManager")
	@Bean(name="druidDataSourceTransactionManager")
	public PlatformTransactionManager druidDataSourceTransactionManager(@Qualifier("druidDataSource") DataSource druidDataSource) {
		return new DataSourceTransactionManager(druidDataSource);
	}
}
