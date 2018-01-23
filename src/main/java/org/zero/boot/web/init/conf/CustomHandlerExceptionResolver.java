package org.zero.boot.web.init.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理拦截器
 * @date 2018年1月22日 下午11:21:08
 * @author zero
 */
@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String requestURL = request.getRequestURL().toString().replace(request.getContextPath(), "");
		ModelAndView model = new ModelAndView();
		model.addObject("exception", ex.getMessage());
		model.addObject("url", requestURL);
		model.setViewName(requestURL);
		logger.warn("Test exception handler, from[{}], url[{}] ", request.getRemoteAddr(), requestURL);
		return model;
	}


}
