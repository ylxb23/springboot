package org.zero.boot.web.init.conf.entity;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * session
 * @date 2018年1月20日 上午1:48:42
 * @author zero
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession(redisNamespace="springboot", maxInactiveIntervalInSeconds=1800)	// redisNamespace has not been implemented
public class RedisHttpSessionConfig {

	@Bean(name="redisTemplate")
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean(name="cacheManager")
	public CacheManager cacheManager(@Qualifier("redisTemplate") RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(1800L);
		return cacheManager;
	}

	@Bean(name="keyGenerator")
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder("cache:address:");
				sb.append(target.getClass().getName());
				sb.append(":");
				sb.append(method.getName());
				sb.append(":");
				for (Object obj : params) {
					sb.append(JSONObject.toJSONString(obj));
					sb.append(".");
				}
				return sb.toString();
			}
		};
	}
}
