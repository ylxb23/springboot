package org.zero.boot.web.init.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 第一个数据源配置
 * @date 2017年11月3日 下午2:36:01
 * @author zero
 */
@Configuration
@MapperScan(basePackages= {"org.zero.boot.dao.first"}, sqlSessionFactoryRef="firstSqlSessionFactory")
public class FirstDataSourceConfiguration {
	
	/**
	 * 第一个数据源
	 * @return
	 */
	@Primary	// 设置为主要数据源
	@Bean(name="firstDataSource")
	@Qualifier("firstDataSource")
	@ConfigurationProperties(prefix="datasource.mysql.first")
	public DataSource firstDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 第一个jdbcTemplate
	 * @param dataSource
	 * @return
	 */
	@Bean(name="firstJdbcTemplate")
	public JdbcTemplate firstJdbcTemplate(@Qualifier("firstDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * 第一个数据源session factory
	 * @return
	 * @throws Exception 
	 */
	@Primary
	@Bean(name="firstSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("firstDataSource") DataSource firstDataSource) throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(firstDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:/mapper/first/*.xml"));
		return bean.getObject();
	}
	
	/**
	 * 第一数据源事务管理器
	 * @return
	 */
	@Bean(name="firstDataSourceTransactionManager")
	public DataSourceTransactionManager firstDataSourceTransactionManager() {
		return new DataSourceTransactionManager(firstDataSource());
	}
	
}
