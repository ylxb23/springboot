package org.zero.boot.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zero.boot.common.NormalStatusEnum;
import org.zero.boot.dao.first.entity.SysUser;
import org.zero.boot.dao.first.entity.SysUserExample;
import org.zero.boot.dao.first.entity.SysUserRole;
import org.zero.boot.dao.first.entity.SysUserRoleExample;
import org.zero.boot.dao.first.repository.SysUserMapper;
import org.zero.boot.dao.first.repository.SysUserRoleMapper;

/**
 * 用户数据请求对象
 * @date 2017年12月28日 下午9:43:11
 * @author zero
 */
@Service
public class ZeroUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = queryUserByUsername(username);
		if(sysUser == null) {
			throw new UsernameNotFoundException("username[" + username + "] is not exist.");
		}
		List<SimpleGrantedAuthority> authorities = queryRolesByUsername(username);
		return User.withUsername(sysUser.getUsername())
				.password(sysUser.getPassword())
				.authorities(authorities)
				.accountLocked(!NormalStatusEnum.isOk(sysUser.getStatus())).build();
	}

	/**
	 * 根据 username获取用户信息
	 * @param username
	 * @return
	 */
	private SysUser queryUserByUsername(String username) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 获取角色列表
	 * @param username
	 * @return
	 */
	private List<SimpleGrantedAuthority> queryRolesByUsername(String username) {
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SysUserRole> list = sysUserRoleMapper.selectByExample(example);
		if(list.isEmpty()) {
			return new ArrayList<>();
		}
		return list.stream().map(item -> new SimpleGrantedAuthority(item.getRolecode())).collect(Collectors.toList());
	}
}
