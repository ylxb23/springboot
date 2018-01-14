package org.zero.boot.web.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zero.boot.dao.first.entity.Person;
import org.zero.boot.dao.second.entity.AppInfo;
import org.zero.boot.domain.model.Visitor;
import org.zero.boot.domain.service.AppInfoService;
import org.zero.boot.domain.service.PersonService;

/**
 * for test
 * @author zero
 */
@Controller
public class GreetingController {
	private Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	private static final String TEMPLATE = "Hello, %s!";
	
	private static AtomicLong counter = new AtomicLong(0);
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private AppInfoService appInfoService;
	
	@ResponseBody
	@RequestMapping(value="/count", method={RequestMethod.POST, RequestMethod.GET})
	public Visitor greeting(@RequestParam(value="name", defaultValue="world") String name) {
		return new Visitor(counter.incrementAndGet(), String.format(TEMPLATE, name));
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
	public String testException(HttpServletRequest request) throws Exception {
		logger.info("Request /testException, remote user[{}], is user in role(ADMIN)[{}], user principal[{}]", request.getRemoteUser(), request.isUserInRole("ADMIN"), request.getUserPrincipal());
		throw new Exception("This is a test exception");
	}
	
	/**
	 * test first data source.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addPerson", method= {RequestMethod.POST})
	public ResponseEntity<Boolean> addPerson(Person person) {
		person.setId(null);
		Assert.hasText(person.getName(), "name can not be empty.");
		Assert.notNull(person.getAge(), "age can not be empty.");
		Assert.hasText(person.getGendar(), "gendar can not be empty.");
		long result = personService.create(person);
		if(result > 0) {
			logger.info("Add person[{}], result");
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}
	
	/**
	 * test first data source.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/allPerson", method= {RequestMethod.GET})
	public ResponseEntity<List<Person>> queryAllPerson() {
		List<Person> list = personService.queryAll();
		return ResponseEntity.ok(list);
	}
	
	/**
	 * test second data source.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/allAppInfo", method= {RequestMethod.GET})
	public ResponseEntity<List<AppInfo>> queryAllAppInfo() {
		List<AppInfo> list = appInfoService.queryAllAppInfo();
		return ResponseEntity.ok(list);
	}
	
}
