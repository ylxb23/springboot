package org.zero.boot.learn.bean.definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 
 * @date 2019年3月27日 上午12:22:38
 * @author zero
 */
public class SophisticateBeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware, EnvironmentAware {
	@SuppressWarnings("unused")
	private BeanFactory beanFactory;
	private Environment env;
	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		String name = env.getProperty("sophisticate.name", "sophisticatedBean");
		String s = env.getProperty("sophisticate.alias", "sophisticated,sophisticatedObject");
		List<String> alias = s == null ? new ArrayList<>() : new ArrayList<String>(Arrays.asList(s.split(",")));
		BeanDefinitionBuilder sophisticatedBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SophisticatedBean.class);
		sophisticatedBeanDefinitionBuilder.addConstructorArgValue(name);
		sophisticatedBeanDefinitionBuilder.addPropertyValue("alias", alias);
		sophisticatedBeanDefinitionBuilder.addPropertyReference("service", "appInfoService");
		registry.registerBeanDefinition(SophisticatedBean.class.getSimpleName(), sophisticatedBeanDefinitionBuilder.getBeanDefinition());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

}
