package com.github.feifuzeng.middleware.redis.jedis;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 基于jedis使用redis 配置类
 * @createTime 2019年09月16日 15:16:00
 */

@Configuration
@PropertySource("classpath:redis.properties")
@Log4j2
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.block-when-exhausted}")
    private boolean blockWhenExhausted;

    @Bean
    public JedisPool redisPoolFactory() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        /** 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true */
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        /** 是否启用pool的jmx管理功能, 默认true */
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = null;
        if (!StringUtils.isEmpty(password)) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);

        }
        log.info("JedisPool[{}:{}]注入成功！！",host,port);
        return jedisPool;
    }

}