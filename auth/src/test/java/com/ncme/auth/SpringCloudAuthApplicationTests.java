package com.ncme.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudAuthApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        // redis存储数据
//        String key = "name1";
//        redisTemplate.opsForSet().add(key, "yukong12");

        boolean result = redisTemplate.opsForSet().isMember("admin","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTk1OTE1NTcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iLCJ1c2VyIiwic2VsZWN0Il0sImp0aSI6ImE3MGI5YzAzLWIwYjctNDE0MC04Y2ZmLWE4MjJhZjZkNmQ2MiIsImNsaWVudF9pZCI6ImNsaWVudDEiLCJzY29wZSI6WyJzZWxlY3QiXX0.WE6lq-3BwTKrj4f_w-NxhG5HBJOlYTuL4usUAWgxzDY");
        System.out.println(result);
        // 获取数据
//        String value = (String) redisTemplate.opsForValue().get(key);
//        System.out.println("获取缓存中key为" + key + "的值为：" + value);
//
//        User user = new User();
//        user.setUsername("yukong");
//        user.setSex(18);
//        user.setId(1L);
//        String userKey = "yukong";
//        redisTemplate.opsForValue().set(userKey, user);
//        User newUser = (User) redisTemplate.opsForValue().get(userKey);
//        System.out.println("获取缓存中key为" + userKey + "的值为：" + newUser);

    }

}
