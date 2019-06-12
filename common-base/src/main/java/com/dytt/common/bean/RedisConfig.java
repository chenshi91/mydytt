package com.dytt.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author chenshi
 * @date 2019-05-27
 */
@Configuration
public class RedisConfig {

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        // redis存取对象的关键配置
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // ObjectRedisSerializer类为java对象的序列化和反序列化工具类
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ValueOperations  valueOperations(RedisTemplate   redisTemplate){
        return redisTemplate.opsForValue();
    }

//    @Bean
//    @Primary
//    public CacheManager cacheManager(RedisTemplate  redisTemplate){
//
//        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
//
//        return cacheManager;
//    }
}
