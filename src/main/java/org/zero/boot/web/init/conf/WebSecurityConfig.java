package org.zero.boot.web.init.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.zero.boot.web.init.conf.security.CustomFailureHandler;
import org.zero.boot.web.init.conf.security.CustomSuccessHandler;

/**
 * spring framework security configurations
 *
 * @author zero
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomSuccessHandler successHandler;
	@Autowired
	private CustomFailureHandler failureHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	/**
	 * 权限角色对应关系
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 测试阶段，去除CSRF(Cross-site request forgery, 跨站请求伪造)攻击预防
		http.csrf().disable();
		// role permission configure
		http.authorizeRequests()
			.antMatchers("/**").permitAll()	// for test 
			.antMatchers("/", "/hello", "/login", "404", "500", "/error").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/account/**").access("hasRole('ADMIN') or hasRole('USER')")
			.anyRequest().authenticated();
		// role permission configure finish
		
//		http.rememberMe();	// rememberMe configuration
//		http.authorizeRequests().expressionHandler(expressionHandler)
		
		
		http.formLogin().permitAll().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
			// 默认 j_username, j_password
			.usernameParameter("username").passwordParameter("password")
			.and()
			.logout().permitAll().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("cookieNamesToClear")
			// rememberMe, parameter default: remember-me
			.and().rememberMe().useSecureCookie(true).rememberMeParameter("rememberme");
		
	}
	
	/**
	 * 用户信息源
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		// 程序加载时加载全部用户信息到内存中
		auth.inMemoryAuthentication()
			// #SimpleGrantedAuthority
			.withUser("user").password("password").roles("USER")
			.and().withUser("admin").password("admin").roles("ADMIN", "USER");
		*/
//		auth.jdbcAuthentication();	// 使用jdbc加载用户信息
		// 使用自定义 AuthenticationProvider, 譬如使用 DaoAuthenticationProvider中的通过实现 UserDetailsService加载用户信息
		auth.userDetailsService(userDetailsService);
	}
}
