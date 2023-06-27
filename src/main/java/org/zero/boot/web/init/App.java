package org.zero.boot.web.init;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.zero.boot.learn.enable.anotaton.EnableReportPkg;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.zero.boot.learn.sentinel.Rules;

/**
 * The application entrance here
 *
 * @author zero
 */
@EnableAsync	// 启用可异步调用，结合使用@Async，异步方法调用综合结果结束异步线程使用#java.util.concurrent.Future.
//@EnableWebMvc
@EnableScheduling
@EnableAspectJAutoProxy
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@EnableAutoConfiguration
@EnableReportPkg({"org.zero.boot.dao.first.repository", 
	"org.zero.boot.dao.second.repository", 
	"org.zero.boot.dao.third.repository", 
	"org.zero.boot.domain.service"})
@SpringBootApplication
@PropertySource(value = {"classpath:application.properties", 
		"classpath:datasource.properties", 
		"classpath:kafka.properties",
		"classpath:plugins.properties"})
@ComponentScan(value = {"org.zero.boot"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)	// redis托管session
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final CountDownLatch counter = new CountDownLatch(1);
	
	public static void main(String[] args) {
		logger.info("App init with args: {}", Arrays.asList(args));
		// 指定日志框架
		System.setProperty(LoggingSystem.SYSTEM_PROPERTY, "org.springframework.boot.logging.log4j2.Log4J2LoggingSystem");
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		logger.info("App started with {} beans inited...", context.getBeanDefinitionCount());
		Rules.initRules();	// 启用 sentinel
		counter.countDown();
	}
	
}
