package com.github.feifuzeng.middleware.redis;

import com.github.feifuzeng.middleware.redis.core.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Redis相关测试类
 * @createTime 2019年09月16日 13:45:00
 */

@Log4j2
public class RedisTest extends SpringbootMiddlewareRedisApplicationTests {

    @Resource
    RedisUtil redisUtil;

    private static final String TEST_REDIS_STRING = "testt";

    @Test
    public void expire() {
        boolean flag = redisUtil.expire(TEST_REDIS_STRING,3);
        log.info(flag);
    }

    @Test
    public void getExpire(){
        long flag = redisUtil.getExpire(TEST_REDIS_STRING);
        log.info(flag);
    }

    @Test
    public void hasKey(){
        boolean flag = redisUtil.hasKey(TEST_REDIS_STRING);
        log.info(flag);
    }

    @Test
    public void del(){
        redisUtil.del(TEST_REDIS_STRING);
    }

    @Test
    public void get(){
        Object object = redisUtil.get(TEST_REDIS_STRING);
        log.info(object.toString());
    }

    @Test
    public void set(){
        boolean object = redisUtil.set(TEST_REDIS_STRING,"tttt");
        log.info(object);
    }

    @Test
    public void set_(){
        boolean object = redisUtil.set(TEST_REDIS_STRING,"tttt",5);
        log.info(object);
    }

}
