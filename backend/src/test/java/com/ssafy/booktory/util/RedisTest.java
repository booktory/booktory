//package com.ssafy.booktory.util;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class RedisTest {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void 레디스_값_설정() {
//        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
//        valueOperations.set("FCM_TOKEN_1", "aiwohjksdf");
//    }
//
//    @Test
//    public void 레디스_값_삭제() {
//        stringRedisTemplate.delete("FCM_TOKEN_1");
//    }
//}
