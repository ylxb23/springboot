package org.zero.boot.web.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zero.boot.util.DateUtil;

/**
 * for test scheduler using
 *
 * @author zero
 */
@Component
public class HeartBeatScheduler {
	private final Logger logger = LoggerFactory.getLogger(HeartBeatScheduler.class);
	
	@Scheduled(cron=" 0 0/1 * * * ? ")
	public void heartBeat() {
		logger.info("Heart beat at {}.", DateUtil.format(new Date()));
	}
	
}
