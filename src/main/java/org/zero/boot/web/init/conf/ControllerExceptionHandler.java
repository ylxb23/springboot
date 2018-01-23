package org.zero.boot.web.init.conf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller层统一异常处理
 * @date 2017年11月2日 下午4:44:21
 * @author zero
 */
@ControllerAdvice(basePackages= {"org.zero.boot"})
public class ControllerExceptionHandler  {
	private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
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
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public void illegalArgumentExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		String msg = e.getMessage();
		logger.error("Got illegal argument exception.", e);
		ResponseEntity<?> entity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);
		try(PrintWriter writer = response.getWriter()) {
			writer.println(entity.toString());
		} catch(IOException ioe) {
			logger.warn("Exection not handled", ioe);
		}
	}
	
}
