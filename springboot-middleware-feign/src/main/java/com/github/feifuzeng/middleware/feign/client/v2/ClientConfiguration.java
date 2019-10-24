package com.github.feifuzeng.middleware.feign.client.v2;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 客户端配置类
 * @createTime 2019年10月23日 15:26:00
 */
public class ClientConfiguration {

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
}