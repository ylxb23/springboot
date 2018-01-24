package org.zero.boot.domain.vo;

import java.util.Set;

/**
 * 用户前端展示信息
 * @date 2018年1月23日 下午11:50:59
 * @author zero
 */
public class UserVo<T> {
	/** 登录用户名 */
	private String username;
	/** 账户是否未被冻结 */
	private Boolean isAccountNonLocked;
	/** 账户是否未过期 */
	private Boolean isAccountNonExpired;
	/** 账户认证是否未过期 */
	private Boolean isCredentialsNonExpired;
	/** 账户是否可用 */
	private Boolean isEnabled;
	/** 用户拥有的权限列表 */
	private Set<String> authorities;
	
	/** 用户拓展信息 */
	private T plus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public T getPlus() {
		return plus;
	}

	public void setPlus(T plus) {
		this.plus = plus;
	}
	
}
