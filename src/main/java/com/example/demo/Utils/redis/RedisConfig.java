package com.example.demo.Utils.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
redis配置类
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * retemplate 相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String ,Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //配置连接工厂
        redisTemplate.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方法）
        Jackson2JsonRedisSerializer jacksonSerial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        //指定要序列化的域 filed,set,get,以及修饰的范围，ANY是都有从private到public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化的输入类型，类必须是非final类型的，final类型的String，Integer会抛出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerial.setObjectMapper(om);

        // 值采用json序列化
        redisTemplate.setValueSerializer(jacksonSerial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jacksonSerial);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 对Hash类的操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations hashOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * 对Redis的字符串类型操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations valueOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForValue();
    }

    /**
     * 对一个链表操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations listOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合的操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations setOperations(RedisTemplate<String,Object>redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合的操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations zSetOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }

}
