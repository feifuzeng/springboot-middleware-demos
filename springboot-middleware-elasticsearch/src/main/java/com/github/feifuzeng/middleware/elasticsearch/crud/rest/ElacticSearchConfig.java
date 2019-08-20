package com.github.feifuzeng.middleware.elasticsearch.crud.rest;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author feifz
 * @version 1.0.0
 * @Description ES配置
 * @createTime 2019年08月19日 17:06:00
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
@Log4j2
@Data
public class ElacticSearchConfig {

    private String host;

    private Integer port;

    private String username;

    private String password;

    /**
     * 初始化client
     * @return
     */
    public RestHighLevelClient initClient() {
        log.info("初始化客户端 ...");
        RestClientBuilder builder =null;
        Assert.hasLength(host, "host不可为空！");
        if (!StringUtils.isEmpty(username) && (!StringUtils.isEmpty(password))) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(username, password));
             builder = RestClient.builder(new HttpHost(host, port))
                    .setHttpClientConfigCallback(
                            httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }else {
             builder = RestClient.builder(new HttpHost(host, port));
        }
        RestHighLevelClient esRestClient = new RestHighLevelClient(builder);
        log.info(esRestClient.indices());
        log.info("初始化客户端完成...");
        return esRestClient;
    }
}
