package org.zero.boot.learn.enable;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.zero.boot.learn.enable.anotaton.EnableReportPkg;

/**
 * 
 * @date 2019年3月21日 上午1:28:17
 * @author zero
 */
public class ReportPkgImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(EnableReportPkg.class.getName());
		String[] pkgs = (String[]) attrs.get("value");
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(BeanReportProcessor.class);
		beanDefinitionBuilder.addPropertyValue("pkgs", pkgs);
		registry.registerBeanDefinition(BeanReportProcessor.class.getName(), beanDefinitionBuilder.getBeanDefinition());
	}

}
