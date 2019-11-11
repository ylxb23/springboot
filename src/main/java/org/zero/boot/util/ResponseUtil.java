package org.zero.boot.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @date 2018年1月24日 上午12:36:32
 * @author zero
 */
public class ResponseUtil {
	private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	/**
	 * write response
	 * @param response
	 * @param obj
	 * @return
	 */
	public static boolean writeResponse(HttpServletResponse response, Object obj) {
		response.setContentType("application/json; charset=utf-8");
		try(PrintWriter writer = response.getWriter()) {
			writer.println(JSON.toJSONString(obj));
			return true;
		} catch(Exception e) {
			logger.error("Exception occurred while write response[{}].", JSON.toJSONString(obj), e);
			return false;
		}
	}
	
}
