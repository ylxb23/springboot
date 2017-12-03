package org.zero.boot.web.init.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	/**
	 * 权限角色对应关系
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// role permission configure
		http.authorizeRequests()
			.antMatchers("/**").permitAll()	// for test 
			.antMatchers("/", "/hello", "404", "500", "/error").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated()
		// role permission configure finish
			.and()
			.formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
			.usernameParameter("username").passwordParameter("password")
			.and()
			.logout().permitAll();
		
	}
	
	/**
	 * 用户信息导入
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("password").roles("hasRole('USER')")
			.and().withUser("zero").password("zeropass").roles("hasRole('USER')");
	}
}
