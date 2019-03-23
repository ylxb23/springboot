package org.zero.boot.learn.enable.anotaton;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.zero.boot.learn.enable.ReportPkgImportBeanDefinitionRegistrar;

/**
 * 
 * @date 2019年3月21日 上午1:16:53
 * @author zero
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ReportPkgImportBeanDefinitionRegistrar.class)
public @interface EnableReportPkg {
	
	@AliasFor("pkgs")
	String[] value() default "";
	
	@AliasFor("value")
	String[] pkgs() default "";
}
