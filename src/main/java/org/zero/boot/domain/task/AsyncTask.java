package org.zero.boot.domain.task;

import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 测试异步任务执行
 * @date 2017年11月3日 下午6:48:44
 * @author zero
 */
@Component
public class AsyncTask {
	
	private Logger logger = LoggerFactory.getLogger(AsyncTask.class);
	
	
	@Async
	public Future<String> taskOne() throws InterruptedException {
		long start = System.currentTimeMillis();
		logger.info("taskOne executing....");
		Thread.sleep(new Random().nextInt(10000));
		logger.info("taskOne over, spend {}ms.", System.currentTimeMillis()-start);
		return new AsyncResult<String>("taskOne execute over.");
	}
	
	@Async
	public Future<String> taskTwo() throws InterruptedException {
		long start = System.currentTimeMillis();
		logger.info("taskTwo executing....");
		Thread.sleep(new Random().nextInt(10000));
		logger.info("taskTwo over, spend {}ms.", System.currentTimeMillis()-start);
		return new AsyncResult<String>("taskTwo execute over.");
	}
	
	@Async
	public Future<String> taskThree() throws InterruptedException {
		long start = System.currentTimeMillis();
		logger.info("taskThree executing....");
		Thread.sleep(new Random().nextInt(10000));
		logger.info("taskThree over, spend {}ms.", System.currentTimeMillis()-start);
		return new AsyncResult<String>("taskThree execute over.");
	}
	
}
