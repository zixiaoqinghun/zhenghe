package com.richard.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.richard.entity.User;

@Configuration
public class RedisConfig {

	@Bean(name="myRedisTemplate")
	public RedisTemplate<Object, User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<Object, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
		redisTemplate.setDefaultSerializer(serializer);
		return redisTemplate;
	}
	
	@Bean(name="myRedisCacheManager")
	public RedisCacheManager redisCacheManager(RedisTemplate<Object, User> redisTemplate){
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setUsePrefix(true);
		cacheManager.setDefaultExpiration(60);
		return cacheManager;
	}
}
