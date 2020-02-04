package com.github.feifuzeng.middleware.feign.client.v2;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 客户端配置类
 * @createTime 2019年10月23日 15:26:00
 */
@Configuration
public class FeignClientConfiguration {

    @Value("${test.token}")
    private String token;

    @Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {
                List<String> authorizationList = Lists.newArrayList(token);
                List<String> contentTypeList = Lists.newArrayList("application/x-www-form-urlencoded;charset=utf-8");
                Map<String, Collection<String>> headers = ImmutableMap.of("token", authorizationList, "Content-Type", contentTypeList);
                template.headers(headers);
            }
        };
    }

    /**
     * 设置打印日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;

    }

    /**
     * 设置重试机制
     *
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }
}