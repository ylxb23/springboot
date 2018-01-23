package org.zero.boot.web.init.conf.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * session
 * @date 2018年1月20日 上午1:48:42
 * @author zero
 */
@Configuration
@EnableRedisHttpSession(redisNamespace="springboot", maxInactiveIntervalInSeconds=1800)	// redisNamespace has not been implemented
public class RedisHttpSessionConfig {

}
