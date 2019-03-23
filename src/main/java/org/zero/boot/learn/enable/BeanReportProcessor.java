package org.zero.boot.learn.enable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 
 * @date 2019年3月21日 上午1:20:18
 * @author zero
 */
public class BeanReportProcessor implements BeanPostProcessor {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String[] pkgs;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		String clazzName = bean.getClass().getName();
		logger.debug("BeanReportProcess, post process before initialization, beanName:{}, beanClass:{}", beanName, clazzName);
		for(String pkg : pkgs) {
			if(bean.getClass().getName().startsWith(pkg)) {
				logger.info("Report bean:{}, clazz:{}", beanName, clazzName);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.debug("BeanReportProcess, post process after initialization, beanName:{}, beanClass:{}", beanName, bean.getClass().getName());
		return bean;
	}

	public String[] getPkgs() {
		return pkgs;
	}

	public void setPkgs(String[] pkgs) {
		this.pkgs = pkgs;
	}

}
