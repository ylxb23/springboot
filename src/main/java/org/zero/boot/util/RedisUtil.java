package org.zero.boot.util;

import java.util.Random;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.zero.boot.web.init.context.ApplicationContextHolder;

/**
 * 基于Redis的分布式锁实现方法
 * @date 2018年1月26日 下午4:01:14
 * @author zero
 */
public class RedisUtil {
	private RedisUtil() {}
	
	public static RedisTemplate<?, ?> redisTemplate = (RedisTemplate<?, ?>) ApplicationContextHolder.application.getBean("redisTemplate");
	
	private static final String LOCK_KEY_PREFIX = "lock:";
	
	private static ThreadLocal<String> LOCK_OWNER_UUID = new ThreadLocal<>();
	
	public static final long DEFAULT_LOCKING_TIME = 30_000L;
	public static final long DEFAULT_WAITING_FOR_LOCK_TIME = 10_000L;
	
	/**
	 * lock with default configuration
	 * waiting lock for 10s blocking time
	 * locking 30s if lock aquired.
	 * @param key
	 * @return
	 */
	public static boolean lock(String key) {
		return lock(key, DEFAULT_WAITING_FOR_LOCK_TIME, DEFAULT_LOCKING_TIME);
	}
	
	/**
	 * acquire blocking lock
	 * @param key
	 * @param waittimeout
	 * @param lockingtime
	 * @return
	 */
	public static boolean lock(String key, long waittimeout, long lockingtime) {
		String uuid = UUID.randomUUID().toString();
		Random random = new Random();
		final String lockKey = LOCK_KEY_PREFIX + key;
		long timeoutpoing = System.currentTimeMillis() + waittimeout;
		while(timeoutpoing > System.currentTimeMillis()) {
			boolean setnx = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				@SuppressWarnings("unchecked")
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] skey = ((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(lockKey);
					byte[] svalue = ( (RedisSerializer<String>) redisTemplate.getValueSerializer()).serialize(uuid);
					// 通过Redis的 setnx原子操作获取到锁
					return connection.setNX(skey, svalue);
				}
			});
			if(setnx) {
				// 获取锁成功
				LOCK_OWNER_UUID.set(uuid);
				return true;
			}
			try {
				// 随机阻塞时间，提升锁竞争公平性，同时防止饥饿线程等待
				Thread.sleep(100 + random.nextInt(300));
			} catch (InterruptedException e) {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * unlock
	 * @param key
	 * @return
	 */
	public static boolean unlock(String key) {
		String lockKey = LOCK_KEY_PREFIX + key;
		final String uuid = LOCK_OWNER_UUID.get();
		if(uuid == null) {
			return false;
		}
		try {
			boolean flag = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				@SuppressWarnings("unchecked")
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] skey = ((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(lockKey);
					String lockuuid = ((RedisSerializer<String>) redisTemplate.getValueSerializer()).deserialize(connection.get(skey));
					if(lockuuid == null) {
						return Boolean.FALSE;
					}
					if(uuid.equals(lockuuid)) {
						LOCK_OWNER_UUID.remove();
						return connection.del(skey) > 0;
					}
					return Boolean.FALSE;
				}
			});
			return flag;
		} finally {
			// 及时清理 ThreadLocalMap
			LOCK_OWNER_UUID.remove();
		}
	}
	
	/**
	 * super unlock
	 * @param key
	 * @return
	 */
	public static boolean superUnlock(String key) {
		String lockKey = LOCK_KEY_PREFIX + key;
		try {
			boolean flag = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				@SuppressWarnings("unchecked")
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] skey = ((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(lockKey);
					return connection.del(skey) > 0;
				}
			});
			return flag;
		} finally {
			// 及时清理 ThreadLocalMap
			LOCK_OWNER_UUID.remove();
		}
	}
	
}
