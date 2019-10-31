package com.github.feifuzeng.middleware.redis.control;

import com.github.feifuzeng.middleware.base.result.PlainResult;
import com.github.feifuzeng.middleware.redis.RedisDistributedTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 验证redis分布式锁 控制器
 * @createTime 2019年10月31日 14:36:00
 */
@RestController
@RequestMapping("/redis")
@Log4j2
public class RedisController {

    @Autowired
    private JedisPool jedisPool;

    private static String REDIS_LOCK_KEY = "locktestkey";

    private static int EXPIRE_TIME = 3000;

    @RequestMapping("/search")
    public PlainResult<String> search(@RequestParam("title") String title){
        PlainResult<String> result = new PlainResult<>();

        String requestId = UUID.randomUUID().toString();
        log.info("requestID为：{}", requestId);
        Jedis jedis = jedisPool.getResource();
        /* 加锁**/
        Boolean lockFlag = RedisDistributedTool.tryGetDistributedLock(jedis, REDIS_LOCK_KEY, requestId, EXPIRE_TIME);
        if(!lockFlag){
            result.setErrorMessage("当前任务繁忙，请稍后重试！");
            return result;
        }
        result.setData(title);
        Boolean releaseFlag = RedisDistributedTool.releaseDistributedLock(jedis, REDIS_LOCK_KEY, requestId);
        log.info("释放锁标识位：{}",releaseFlag);
        return result;
    }
}
