package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @title: RedisConfig
 * @Description Redis配置文件
 * @Author ZLT
 * @Date: 2021/12/31 9:46
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    /**
     * 配置RedisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //value序列化
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }

    /**
     * 操作字符串类型数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<Object, Object> valueOperations(RedisTemplate<Object, Object> redisTemplate){
        return redisTemplate.opsForValue();
    }

    /**
     * 操作Hash类型数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<Object, Object, Object> hashOperations(RedisTemplate<Object, Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * 操作链表List类型数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<Object, Object> listOperations(RedisTemplate<Object,Object> redisTemplate){
        return redisTemplate.opsForList();
    }

    /**
     * 操作无序集合Set类型数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<Object, Object> setOperations(RedisTemplate<Object, Object> redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     * 操作有序集合ZSet类型数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<Object, Object> zSetOperations(RedisTemplate<Object, Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
