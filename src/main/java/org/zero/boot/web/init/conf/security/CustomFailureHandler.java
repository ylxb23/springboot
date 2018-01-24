package org.zero.boot.web.init.conf.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.zero.boot.domain.response.LoginResponse;
import org.zero.boot.util.ResponseUtil;

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
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("username");
		// count failure times
		
		doWriteReponse(request, response, exception);
	}
	
	/**
	 * In use
	 * @param request
	 * @param response
	 * @param exception
	 */
	protected void doWriteReponse(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		String failure = null;
		if(exception instanceof UsernameNotFoundException) {
			failure = "用户名不存在";
		} else if(exception instanceof AccountStatusException) {
			failure = "账户状态不可用";
		} else if(exception instanceof BadCredentialsException) {
			failure = "密码错误";
		} else {
			// Others
			failure = "账户存在风险";
		}
		LoginResponse resObj = LoginResponse.builder()
				.code(LoginResponse.CODE_FAILURE)
				.msg(failure)
				.build();
		ResponseUtil.writeResponse(response, resObj);
	}
	
	/**
	 * @deprecated 前后端分离,弃用后端跳转链接
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	@Deprecated
	protected void doSendRedirect(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
		
	}
	
}
