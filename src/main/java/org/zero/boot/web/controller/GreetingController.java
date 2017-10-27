package org.zero.boot.web.controller;

import java.util.concurrent.atomic.AtomicLong;

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
}
