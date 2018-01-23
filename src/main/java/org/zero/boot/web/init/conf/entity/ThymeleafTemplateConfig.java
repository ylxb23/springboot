package org.zero.boot.web.init.conf.entity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Thymeleaf template configuration beans
 * @date 2018年1月23日 下午10:23:04
 * @author zero
 */
@Configuration
public class ThymeleafTemplateConfig {
	
	static final String TEMPLATE_LOCATION = "classpath:/templates/";
	
	@Bean(name="htmlTemplateResolver")
	public TemplateResolver servletContextTemplateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix(TEMPLATE_LOCATION);
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}
	
	@Bean(name="templateEngine")
	public SpringTemplateEngine templateEngine(@Qualifier("htmlTemplateResolver") TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(@Qualifier("templateEngine") SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] {"*.html", "*.xhtml", "*.htm"});
		return viewResolver;
	}
	
	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
		springResourceTemplateResolver.setSuffix(".html");
		springResourceTemplateResolver.setTemplateMode("HTML5");
		return springResourceTemplateResolver;
	}
}
