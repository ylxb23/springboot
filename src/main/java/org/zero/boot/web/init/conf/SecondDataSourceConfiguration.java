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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 
 * @date 2017年11月3日 下午2:58:30
 * @author zero
 */
@Configuration
@MapperScan(basePackages= {"org.zero.boot.dao.second"}, sqlSessionFactoryRef="secondSqlSessionFactory")
public class SecondDataSourceConfiguration {

	/**
	 * 第二个数据源
	 * @return
	 */
	@Bean(name="secondDataSource")
	@Qualifier("secondDataSource")
	@ConfigurationProperties(prefix="datasource.mysql.second")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 第二个jdbcTemplate
	 * @param dataSource
	 * @return
	 */
	@Bean(name="secondJdbcTemplate")
	public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	/**
	 * 第二个数据源session factory
	 * @return
	 * @throws Exception 
	 */
	@Bean(name="secondSqlSessionFactory")
	public SqlSessionFactory secondSqlSessionFactoryBean(@Qualifier("secondDataSource") DataSource secondDataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(secondDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:/mapper/second/*.xml"));
		return bean.getObject();
	}

	/**
	 * 第二数据源事务管理器
	 */
	@Bean(name="secondDataSourceTransactionManager")
	public DataSourceTransactionManager secondDataSourceTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
