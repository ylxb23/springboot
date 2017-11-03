package org.zero.test.web;

import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zero.boot.domain.task.AsyncTask;
import org.zero.test.TestBasic;

/**
 * test for @EnableAsync 
 * @date 2017年11月3日 下午6:34:29
 * @author zero
 */
public class TestAsync extends TestBasic {
	private Logger logger = LoggerFactory.getLogger(TestAsync.class);
	
	@Autowired
	private AsyncTask asyncTask;
	
	/**
	 * 通过异步执行方式执行效率更高
	 * taskTwo executing....
	 * taskOne executing....
	 * taskThree executing....
	 * taskThree over, spend 2741ms.
	 * taskTwo over, spend 3530ms.
	 * taskOne over, spend 9961ms.
	 * all task done. spend 9987ms
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testSync() throws InterruptedException {
		long start = System.currentTimeMillis();
		Future<String> taskOne = asyncTask.taskOne();
		Future<String> taskTwo = asyncTask.taskTwo();
		Future<String> taskThree = asyncTask.taskThree();
		while(true) {
			if(taskOne.isDone() && taskTwo.isDone() && taskThree.isDone()) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		logger.info("all task done. spend {}ms", (end - start));
	}
	
}
