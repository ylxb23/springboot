package org.zero.boot.web.init.conf;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * @date 2017年11月2日 下午4:44:21
 * @author zero
 */
@ControllerAdvice
public class GlobalExceptionHandler  {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private static final String ERROR_PATH = "/error";
	
	@ExceptionHandler(value = {Exception.class})
	public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception e) {
		ModelAndView model = new ModelAndView();
		model.addObject("exception", e.getMessage());
		model.addObject("url", request.getRequestURL().toString());
		model.setViewName(ERROR_PATH);
		logger.warn("Test exception handler, from[{}], url[{}] ", request.getRemoteAddr(), request.getRequestURL());
		return model;
	}
	
}
