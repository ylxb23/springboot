package org.zero.boot.web.init.conf.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @date 2018年6月29日 上午12:25:02
 * @author zero
 */
@Configuration
public class AppInfo {
	
	
	@Bean
	public Package packageInfo() {
		Package pkg = Package.getPackage("org.zero.boot");
		return pkg;
	}
	
	@Bean
	public Class<?> mainClass() {
		try {
			return Class.forName("org.zero.boot.web.init.App");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("class not found.");
		}
	}
}
