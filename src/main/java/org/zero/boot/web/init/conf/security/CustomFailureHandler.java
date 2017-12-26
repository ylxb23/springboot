package org.zero.boot.web.init.conf.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 登录失败handler
 * @date 2017年11月17日 下午4:37:38
 * @author zero
 */
@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	public CustomFailureHandler() {
		super();
		custom();
	}

	private void custom() {
		// 在 请求头中将登陆失败的异常放到 属性中
		setUseForward(true);
		// 同时把一场放到session中
		setAllowSessionCreation(true);
	}
	
}
