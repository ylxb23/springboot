package org.zero.boot.web.init;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The application entrance here
 *
 * @author zero
 */
@EnableWebMvc
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
@PropertySource(value = {"classpath:application.properties", "classpath:datasource.properties"})
@ComponentScan(value = {"org.zero.boot"})
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	
	public static void main(String[] args) {
		logger.info("App init with args: {}", Arrays.asList(args));
		SpringApplication.run(App.class, args);
	}
	
}
