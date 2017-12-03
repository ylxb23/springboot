package org.zero.boot.web.init.conf.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 登录成功handler
 * @date 2017年11月17日 下午4:37:05
 * @author zero
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	public CustomSuccessHandler() {
		super();
		custom();
	}
	private void custom() {
		setUseReferer(true);
		setTargetUrlParameter("targetUrl");
	}
	
	
}
