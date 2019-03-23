package org.zero.boot.web.init.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 程序终止时执行
 * @date 2018年12月11日 上午12:06:30
 * @author zero
 */
@Component
public class ShutdownHookThread extends Thread {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private volatile boolean shutdownSingnalRecived;
	private Thread main;
	
	public ShutdownHookThread() {
		this.shutdownSingnalRecived = false;
		this.main = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(this);
	}
	
	@Override
	public void run() {
		logger.warn("Start to shutdown...");
		this.shutdownSingnalRecived = true;
		try {
			main.interrupt();
			logger.info("doing while shutdowning...[{}]", main.getName());
			main.join();
			Thread.sleep(300L);
			logger.info("done while shutdowning...");
		} catch(Exception e) {
			logger.error("shutdown.", e);
		}
		logger.warn("Shutdowned...");
	}
	
	public boolean isShutingdown() {
		return this.shutdownSingnalRecived;
	}
}
