package org.zero.boot.web.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zero.boot.domain.model.RunningWaterEntry;
import org.zero.boot.domain.request.RunningWaterPostRequest;
import org.zero.boot.domain.service.RunningWaterService;
import org.zero.boot.util.DateUtil;

/**
 * 
 * @date 2017年11月20日 下午9:35:42
 * @author zero
 */
@RestController
@RequestMapping(value="/account")
public class AccountRunningController {
	private final Logger logger = LoggerFactory.getLogger(AccountRunningController.class);
	
	@Autowired
	private RunningWaterService runningWaterService;
	
	@ResponseBody
	@RequestMapping(value="/income", method= {RequestMethod.POST, RequestMethod.GET}) 
	public ResponseEntity<Boolean> income(RunningWaterPostRequest income) {
		if(income.getDatetime() == null) {
			income.setDatetime(new Date());
		}
		logger.info("user[{}] income[{}] at[{}] for[{} - {}].", income.getOwner(), income.getAmount(), DateUtil.format(income.getDatetime()), income.getForname(), income.getTitle());
		return ResponseEntity.ok(runningWaterService.putIncome(income));
	}
	
	@ResponseBody
	@RequestMapping(value="/outcome", method= {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<Boolean> outcome(RunningWaterPostRequest outcome) {
		if(outcome.getDatetime() == null) {
			outcome.setDatetime(new Date());
		}
		logger.info("user[{}] outcome[{}] at[{}] for[{} - {}].", outcome.getOwner(), outcome.getAmount(), DateUtil.format(outcome.getDatetime()), outcome.getForname(), outcome.getTitle());
		return ResponseEntity.ok(runningWaterService.putOutcome(outcome));
	}
	
	@ResponseBody
	@RequestMapping(value="/review", method= {RequestMethod.GET})
	public ResponseEntity<List<RunningWaterEntry>> review() {
		return ResponseEntity.ok(runningWaterService.reviewAll());
	}
}
