package org.zero.boot.web.info;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * application health check
 * @date 2017年11月2日 下午2:41:38
 * @author zero
 */
public class AppHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().build();
	}

}
