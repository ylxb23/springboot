package org.zero.boot.web.init.conf.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录成功handler
 * @date 2017年11月17日 下午4:37:05
 * @author zero
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);
	
	public CustomSuccessHandler() {
		super();
		custom();
	}
	
	private void custom() {
		// 如果登陆时参数中没有找到 fromUrl，则从高 Header中的 "Referer"中找登录成功后跳转的 url
		setUseReferer(true);
		// 登陆时将登录成功要跳转的路径传入的参数名，譬如 /login?fromUrl=/user/home
		setTargetUrlParameter("fromUrl");
	}
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// store user info into sesssion
		sessionUserInfo(request, authentication);
		
		String fromUrl = request.getParameter("fromUrl");
		if(StringUtils.isEmpty(fromUrl)) {
			// 如果参数中没有 fromUrl,则通过权限中获取的角色跳转到对应的 HOME路径下
			// 从 handle中获取 request,response,authentication对象
			// SimpleGrantedAuthority
			@SuppressWarnings("unchecked")
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();	// 只有一个角色
			if(authorities.isEmpty()) {
				// 嘿嘿，这是不可能的，因为我会写死他
				User user = (User) authentication.getPrincipal();
				logger.warn("用户[{}]权限有问题,他没角色！", user.getUsername());
				throw new IllegalArgumentException("用户权限有问题");
			}
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority) Arrays.asList(authorities.toArray()).get(0);
			String toUrl = null; 
			// 使用 Builder创建的GrantedAuthority 会有 "ROLE_"这个前缀，是因为在 SimpleGrantedAuthority对象创建的时候 UserBuilder会给它加上去.
			// 当前 UserDetails对象是通过自定义的 UserDetailsService装配的，其中的 authorities也是自定义的，并没有 ROLE_ 前缀
			switch (authority.getAuthority()) {
			case "USER":
				toUrl = "/user/home";
				break;
			case "ADMIN":
				toUrl = "/admin/home";
				break;
			default:
				toUrl = "/";
				break;
			}
			toUrl = response.encodeRedirectURL(toUrl);
			response.sendRedirect(toUrl);
			return;
		} else {
			// 否则使用框架中的 handle
			super.handle(request, response, authentication);
		}
	}
	
	private void sessionUserInfo(HttpServletRequest request, Authentication authentication) {
		HttpSession session = request.getSession(true);
		Map<String, Object> map = new HashMap<>(4);
		map.put("username", authentication.getPrincipal());
		map.put("authorities", authentication.getAuthorities());
		session.setAttribute("authentication", map);
	}
}
