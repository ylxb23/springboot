package org.zero.test.plugin;

import org.junit.Test;
import org.zero.boot.util.RedisUtil;
import org.zero.test.TestBasic;

/**
 * 
 * @date 2018年1月26日 下午5:41:23
 * @author zero
 */
public class RedisTest extends TestBasic {
	
	static String key = "thisisalockkey";
	
	@Test
	public void test() throws InterruptedException {
		Thread a = new TestThread("aaa");
		Thread b = new TestThread("bbb");
		Thread c = new TestThread("ccc");
		
		a.start();
		b.start();
		c.start();
		a.join();
		b.join();
		c.join();
		
		System.out.println("over...");
	}
	
	static class TestThread extends Thread {
		public TestThread(String n) {
			super(n);
		}
		
		@Override
		public void run() {
			try {
				if(RedisUtil.lock(key, 10_000L, 30_000L)) {
					System.out.println(getName() + " get lock, executing...");
					try {
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(getName() + " get lock, execut finish.");
				} else {
					System.out.println(getName() + " can not get lock.");
				}
			} finally {
				System.out.println(getName() + " unlock: " + RedisUtil.unlock(key));
			}
		}
	}
	
}
