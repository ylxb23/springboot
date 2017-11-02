package org.zero.boot.web.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zero.boot.entity.Person;

/**
 * for test
 * @author zero
 */
@Controller
public class GreetingController {
	private Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	private static final String TEMPLATE = "Hello, %s!";
	
	private static AtomicLong counter = new AtomicLong(0);
	
	@ResponseBody
	@RequestMapping(value="/count", method={RequestMethod.POST, RequestMethod.GET})
	public Person greeting(@RequestParam(value="name", defaultValue="world") String name) {
		return new Person(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}
	
	@RequestMapping(value="/", method= {RequestMethod.GET}) 
	public String turn2Hello() {
		return "hello";
	}
	
	@ResponseBody
	@RequestMapping(value="/test", method= {RequestMethod.GET})
	public String testLogLevel() {
		logger.error("this is error level of logger.");
		logger.warn("this is warn level of logger.");
		logger.info("this is info level of logger.");
		logger.debug("this is debug level of logger.");
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="/testException", method= {RequestMethod.GET})
	public String testException() throws Exception {
		throw new Exception("This is a test exception");
	}
	
}
