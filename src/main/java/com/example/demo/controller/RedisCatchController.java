package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisCatchController {

    @Autowired
    RedisTemplate<String,String>redisTemplate;

    @Test
    public  void catchRedis(){
        String test = redisTemplate.opsForValue().get("test");

        System.out.println(test);
    }

}
