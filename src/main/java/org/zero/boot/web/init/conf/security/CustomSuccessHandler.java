package org.zero.boot.web.init.conf.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.zero.boot.dao.first.entity.SysUser;
import org.zero.boot.dao.first.entity.SysUserExample;
import org.zero.boot.dao.first.repository.SysUserMapper;
import org.zero.boot.domain.response.LoginResponse;
import org.zero.boot.domain.vo.UserPlus;
import org.zero.boot.domain.vo.UserVo;
import org.zero.boot.util.ResponseUtil;

/**
 * 登录成功handler
 * @date 2017年11月17日 下午4:37:05
 * @author zero
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);
	
	@Autowired
	private SysUserMapper userMapper;
	
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
		doWriteResponse(request, response, authentication);
	}
	
	/**
	 * In use
	 * @param request
	 * @param response
	 * @param authentication
	 */
	@SuppressWarnings("unchecked")
	protected void doWriteResponse(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// get from url
		String fromUrl = determineTargetUrl(request, response);
		List<SimpleGrantedAuthority> simpleauthorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
		UserVo<UserPlus> vo = new UserVo<>();
		User user = (User) authentication.getPrincipal();
		vo.setUsername(user.getUsername());
		vo.setIsAccountNonExpired(user.isAccountNonExpired());
		vo.setIsAccountNonLocked(user.isAccountNonLocked());
		vo.setIsCredentialsNonExpired(user.isCredentialsNonExpired());
		vo.setIsEnabled(user.isEnabled());
		Set<String> authorities = simpleauthorities.stream().map(s -> s.getAuthority()).collect(Collectors.toSet());
		vo.setAuthorities(authorities);
		LoginResponse resObj = LoginResponse.builder()
				.fromUrl(fromUrl)
				.code(LoginResponse.CODE_SUCCESS)
				.msg(LoginResponse.MSG_SUCCESS)
				.user(vo).build();
		vo.setPlus(getPlusByUsername(vo.getUsername()));
		ResponseUtil.writeResponse(response, resObj);
	}
	
	/**
	 * 获取用户附加信息
	 * @param username
	 * @return
	 */
	private UserPlus getPlusByUsername(String username) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SysUser> list = userMapper.selectByExample(example);
		if(list.isEmpty()) {
			// could not happen
			throw new AuthenticationCredentialsNotFoundException("用户信息不存在");
		}
		SysUser user = list.get(0);
		UserPlus plus = new UserPlus();
		plus.setEmail(user.getEmail());
		plus.setGendar(user.getGendar());
		plus.setMark(user.getMark());
		plus.setPhone(user.getPhone());
		plus.setRealname(user.getRealname());
		return plus;
	}

	/**
	 * 登录成功，根据角色跳转页面
	 * @deprecated 前后端分离，舍弃页面跳转方式返回
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	@Deprecated
	protected void doSendRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
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
			// 第一个角色为主要角色
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
