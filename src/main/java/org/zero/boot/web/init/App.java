package org.zero.boot.web.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(value={"org.zero.boot"})
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
