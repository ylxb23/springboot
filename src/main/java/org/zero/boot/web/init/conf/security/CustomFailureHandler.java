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

}
