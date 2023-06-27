package org.zero.boot.web.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
import org.zero.boot.dao.third.entity.BbsReply;
import org.zero.boot.domain.model.Visitor;
import org.zero.boot.domain.service.AppInfoService;
import org.zero.boot.domain.service.PersonService;
import org.zero.boot.domain.service.ThirdDsService;
import org.zero.boot.learn.sentinel.Rules;
import org.zero.boot.util.DateUtil;
import org.zero.boot.util.RedisUtil;

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
	
	@Autowired
	private ThirdDsService thirdDsService;

	@ResponseBody
	@RequestMapping(value = "/qps", method = {RequestMethod.GET, RequestMethod.POST})
	public String qpsTest(@RequestParam(value = "from") String from) {
		Entry entry = null;
		String timeNow = DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN_yyyy_MM_dd_HH_mm_ss);
		try {
			entry = SphU.entry(Rules.RESOURCE_A001);
			logger.info("qps request at: {}", timeNow);

		} catch (BlockException e) {
			logger.error("qps request blocked: {}", timeNow, e);
			timeNow += " " + e.getMessage();
		} finally {
			if(entry != null) {
				entry.exit();
			}
		}
		return timeNow;
	}
	
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
		logger.trace("this is trace level of logger.");
		return "";
	}
	
	/**
	 * 尝试获取锁
	 * @param request
	 * @param key
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/aquireLock", method= {RequestMethod.GET})
	public ResponseEntity<String> aquireLock(HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="DEFAULT_KEY", name="key") String key, 
			@RequestParam(required=false, defaultValue="10000", name="time") long time) {
		try {
			boolean lock = RedisUtil.lock(key);
			if(lock) {
				Thread.sleep(time);
				logger.info("Get lock session[{}] with key[{}], time[{}]", request.getSession(true).getId(), key, time);
				return ResponseEntity.ok("GOT: session[" + request.getSession().getId() + "] with key[" + key + "], time[" + time + "]");
			} else {
				logger.info("Not get lock session[{}] with key[{}], time[{}]", request.getSession(true).getId(), key, time);
				return ResponseEntity.ok("NO_GOT: session[" + request.getSession().getId() + "] with key[" + key + "], time[" + time + "]");
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok("ERROR: session[" + request.getSession().getId() + "] with key[" + key + "], time[" + time + "]");
		} finally {
			RedisUtil.unlock(key);
		}
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
	
	/**
	 * test third data source.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getReplies", method= {RequestMethod.GET})
	public ResponseEntity<List<BbsReply>> queryRplies(HttpServletRequest request, 
			@RequestParam(required=true) Long tid) {
		List<BbsReply> list = thirdDsService.getReplies(tid);
		return ResponseEntity.ok(list);
	}
}
