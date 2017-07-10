package org.zero.boot.web.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zero.boot.entity.Person;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	
	private static AtomicLong counter = new AtomicLong(0);
	
	@RequestMapping(value="/", method={RequestMethod.POST, RequestMethod.GET})
	public Person greeting(@RequestParam(value="name", defaultValue="world") String name) {
		return new Person(counter.incrementAndGet(), String.format(template, name));
	}
}
