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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller层统一异常处理
 * @date 2017年11月2日 下午4:44:21
 * @author zero
 */
@ControllerAdvice(basePackages= {"org.zero.boot"})
public class ControllerExceptionHandler  {
	private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(value = {Exception.class})
	public ModelAndView defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ModelAndView model = new ModelAndView();
		response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		model.addObject("exception", e.getMessage());
		String requestUrl = request.getRequestURL().toString();
		model.addObject("url", requestUrl);
		model.setViewName(requestUrl);
		logger.warn("Test exception handler, from[{}], url[{}] , exception: {}", request.getRemoteAddr(), request.getRequestURL(), e.getMessage());
		return model;
	}
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public void illegalArgumentExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		String msg = e.getMessage();
		logger.error("Got illegal argument exception.", e);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		ResponseEntity<?> entity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);
		try(PrintWriter writer = response.getWriter()) {
			writer.println(entity.toString());
		} catch(IOException ioe) {
			logger.warn("Exection not handled", ioe);
		}
	}
	
}
