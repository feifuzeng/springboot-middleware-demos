package com.github.feifuzeng.middleware.elasticsearch.crud.rest.core;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author feifz
 * @version 1.0.0
 * @Description ES配置
 * @createTime 2019年08月21日 09:17:00
 */
@Configuration
@ComponentScan(basePackageClasses = ElasticSearchClientSpringFactory.class)
@Data
public class ElasticSearchConfig {

    @Value("${elasticSearch.core.host}")
    private String host;

    @Value("${elasticSearch.core.port}")
    private int port;

    @Value("${elasticSearch.core.username}")
    private String username;

    @Value("${elasticSearch.core.password}")
    private String password;

    @Value("${elasticSearch.core.client.connectNum}")
    private Integer connectNum;

    @Value("${elasticSearch.core.client.connectPerRoute}")
    private Integer connectPerRoute;

    @Bean
    public HttpHost httpHost() {
        return new HttpHost(host, port, "http");
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public ElasticSearchClientSpringFactory getFactory() {
        return ElasticSearchClientSpringFactory.
                build(httpHost(), username, password, connectNum, connectPerRoute);
    }

    @Bean
    @Scope("singleton")
    public RestHighLevelClient getRHLClient() {
        return getFactory().getRhlClient();
    }

}
