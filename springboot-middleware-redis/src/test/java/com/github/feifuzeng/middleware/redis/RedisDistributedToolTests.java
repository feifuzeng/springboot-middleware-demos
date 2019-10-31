package com.github.feifuzeng.middleware.redis;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 分布式锁-工具类-测试类
 * @createTime 2019年10月31日 14:09:00
 */
@Log4j2
public class RedisDistributedToolTests extends SpringbootMiddlewareRedisApplicationTests {


    @Autowired
    private JedisPool jedisPool;

    private static String REDIS_LOCK_KEY = "locktestkey1";

    private static String REDIS_LOCK_VALUE = "locktestvalue";

    private static int EXPIRE_TIME = 30000;

    private static String REQUEST_ID = "feifuzeng@123456789";
//    private String REQUEST_ID = UUID.randomUUID().toString();


    @Test
    public void lock() {


        log.info("requestID为：{}", REQUEST_ID);
        Jedis jedis = jedisPool.getResource();

        /* 加锁**/
        Boolean lockFlag = RedisDistributedTool.tryGetDistributedLock(jedis, REDIS_LOCK_KEY, REQUEST_ID, EXPIRE_TIME);

        log.info("获取到分布式锁标志：{}", lockFlag);
        if (!lockFlag) {
            log.info("加锁失败，请稍后重试。。。");
            return;
        }
        log.info("-----------------------------");
        log.info("-----业务逻辑出来开始-----");
        log.info("-----业务逻辑处理结束----------");
        log.info("-----------------------------");

//        Boolean releaseFlag = RedisDistributedTool.releaseDistributedLock(jedis, REDIS_LOCK_KEY, requestId);
//        log.info("释放到分布式锁标志：{}", releaseFlag);
        log.info("结束。。。。");
    }

    @Test
    public void release(){
        log.info("requestID为：{}", REQUEST_ID);
        Jedis jedis = jedisPool.getResource();
        Boolean releaseFlag = RedisDistributedTool.releaseDistributedLock(jedis, REDIS_LOCK_KEY, REQUEST_ID);
//        log.info("释放到分布式锁标志：{}", releaseFlag);
    }
}
