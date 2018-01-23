package org.zero.boot.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.CredentialsExpiredException;
import org.zero.boot.common.CommonConstants;

/**
 * 
 * @date 2018年1月20日 上午2:31:19
 * @author zero
 */
public abstract class BaseController {
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public String getLoginUsername(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) session.getAttribute(CommonConstants.SESSION_KEY_USER_INFO);
			if(map != null) {
				return (String) map.get("username");
			}
		}
		throw new CredentialsExpiredException("您还未登录");
	}
	
}
