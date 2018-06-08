package org.zero.boot.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zero.boot.dao.first.entity.SysUser;
import org.zero.boot.web.controller.base.BaseController;

/**
 * 
 * @date 2017年12月28日 下午10:30:14
 * @author zero
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@ResponseBody
	@RequestMapping(value="/user", method= {RequestMethod.PUT})
	public ResponseEntity<String> addUser(HttpServletRequest request, SysUser sysUser) {
		String operator = getLoginUsername(request);
		sysUser.setCreator(operator);
		sysUser.setCreatetime(new Date());
		logger.info("[{}]添加用户[{}]", operator, sysUser.getUsername());
		// TODO
		return ResponseEntity.ok().body("添加成功");
	}

	@ResponseBody
	@RequestMapping(value="/user", method= {RequestMethod.DELETE})
	public ResponseEntity<?> deleteUsers(HttpServletRequest request, String userId) {
		String operator = getLoginUsername(request);
		logger.info("[{}]删除用户[{}]", operator, userId);
		// TODO
		return ResponseEntity.ok().build();
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method= {RequestMethod.POST})
	public ResponseEntity<?> updateUser(HttpServletRequest request, SysUser sysUser) {
		String operator = getLoginUsername(request);
		sysUser.setUpdator(operator);
		sysUser.setUpdatetime(new Date());
		logger.info("[{}]修改用户[{}]", operator, sysUser.getUsername());
		// TODO 
		return ResponseEntity.ok().build();
	}
	
	@ResponseBody
	@RequestMapping(value="/users", method= {RequestMethod.GET})
	public ResponseEntity<?> getUsers(HttpServletRequest request) {
		String operator = getLoginUsername(request);
		logger.info("[{}]查询所有用户", operator);
		// TODO
		return ResponseEntity.ok().build();
	}
	
}
