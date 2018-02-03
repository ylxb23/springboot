package org.zero.boot.web.init.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * application context holder
 * @date 2018年1月26日 下午3:59:58
 * @author zero
 */
@Service
public class ApplicationContextHolder implements ApplicationContextAware {

	public static ApplicationContext application;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		application = applicationContext;
		System.out.println("application inited.");
	}

	
}
