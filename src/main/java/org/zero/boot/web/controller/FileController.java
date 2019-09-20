package org.zero.boot.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zero.boot.web.controller.base.BaseController;

/**
 * 
 * @date 2019年2月23日 下午4:33:10
 * @author zero
 */
@Controller
@RequestMapping(path="/file")
public class FileController extends BaseController {
	
	
	@RequestMapping(path="/upload", method= {})
	public ResponseEntity<?> upload(MultipartHttpServletRequest request) {
		return ResponseEntity.ok("ok");
	}
	
}
