package com.github.feifuzeng.middleware.redis;

import com.github.feifuzeng.middleware.redis.jedis.RedisUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description jedis 操作redis测试类
 * @createTime 2019年09月16日 15:19:00
 */
@Log4j2
public class JedisToolTests extends SpringbootMiddlewareRedisApplicationTests{

    @Resource
    private RedisUtils redisUtils;

    private static final String TEST_REDIS_STRING = "test";

    /**
     * 插入
     */
    @Test
    public void set(){
        String result = redisUtils.set(TEST_REDIS_STRING, "testeee",0);
        log.info(result);
    }

    /**
     * 查询
     */
    @Test
    public void get(){
        String result = redisUtils.get(TEST_REDIS_STRING, 1);
        log.info(result);
    }

    /**
     * 删除
     */
    @Test
    public void del(){
        Long result = redisUtils.del(TEST_REDIS_STRING);
        log.info(result);
    }

    /**
     * 判断是否存在
     */
    @Test
    public void exists(){
        Boolean result = redisUtils.exists(TEST_REDIS_STRING);
        log.info(result);
    }

    /**
     * 设置失效时间
     */
    @Test
    public void expire(){
        Long result = redisUtils.expire(TEST_REDIS_STRING,5,0);
        log.info(result);
    }

    /**
     * 批量插入
     */
    @Test
    public void mset(){
        List<String> par = new ArrayList<>();
        String result = redisUtils.mset();
        log.info(result);
    }
}
