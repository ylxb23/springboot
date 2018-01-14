package org.zero.boot.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zero.boot.dao.first.entity.SysUser;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @date 2017年12月28日 下午10:30:14
 * @author zero
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@ResponseBody
	@RequestMapping(value="/addUser", method= {RequestMethod.POST})
	public ResponseEntity<String> addUser(HttpServletRequest request, SysUser sysUser) {
		// TODO
		logger.info("添加用户请求参数: {}", JSONObject.toJSONString(sysUser));
		return ResponseEntity.ok().body("添加成功");
	}
	
}
