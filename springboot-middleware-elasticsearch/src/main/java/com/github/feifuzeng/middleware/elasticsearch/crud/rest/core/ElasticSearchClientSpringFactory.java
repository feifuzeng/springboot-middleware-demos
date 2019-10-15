package com.github.feifuzeng.middleware.elasticsearch.crud.rest.core;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author feifz
 * @version 1.0.0
 * @Description ES客户端工厂
 * @createTime 2019年08月21日 09:17:00
 */
@Log4j2
public class ElasticSearchClientSpringFactory {

    public static int CONNECT_TIMEOUT_MILLIS = 1000;
    public static int SOCKET_TIMEOUT_MILLIS = 30000;
    public static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
    public static int MAX_CONN_PER_ROUTE = 10;
    public static int MAX_CONN_TOTAL = 30;

    private static HttpHost HTTP_HOST;

    private static String USERNAME;
    private static String PASWORD;
    private RestClientBuilder builder;
    private RestHighLevelClient restHighLevelClient;

    private static ElasticSearchClientSpringFactory esClientSpringFactory = new ElasticSearchClientSpringFactory();

    private ElasticSearchClientSpringFactory() {
    }

    public static ElasticSearchClientSpringFactory build(HttpHost httpHost, String username, String password,
                                                         Integer maxConnectNum, Integer maxConnectPerRoute) {
        USERNAME = username;
        PASWORD = password;
        HTTP_HOST = httpHost;
        MAX_CONN_TOTAL = maxConnectNum;
        MAX_CONN_PER_ROUTE = maxConnectPerRoute;
        return esClientSpringFactory;
    }

    public static ElasticSearchClientSpringFactory build(HttpHost httpHost, String username, String password, Integer connectTimeOut, Integer socketTimeOut,
                                                         Integer connectionRequestTime, Integer maxConnectNum, Integer maxConnectPerRoute) {

        USERNAME = username;
        PASWORD = password;
        HTTP_HOST = httpHost;
        CONNECT_TIMEOUT_MILLIS = connectTimeOut;
        SOCKET_TIMEOUT_MILLIS = socketTimeOut;
        CONNECTION_REQUEST_TIMEOUT_MILLIS = connectionRequestTime;
        MAX_CONN_TOTAL = maxConnectNum;
        MAX_CONN_PER_ROUTE = maxConnectPerRoute;
        return esClientSpringFactory;
    }


    public void init() {
        log.info("初始化客户端 ...");
        builder = RestClient.builder(HTTP_HOST);
        setConnectTimeOutConfig();
        setMutiConnectConfig();

        if (!StringUtils.isEmpty(USERNAME) && (!StringUtils.isEmpty(PASWORD))) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(USERNAME, PASWORD));
            builder = builder
                    .setHttpClientConfigCallback(
                            httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        restHighLevelClient = new RestHighLevelClient(builder);
        log.info("初始化客户端完成...");

    }

    /**
     * 配置连接时间延时
     */
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        });
    }

    /**
     * 使用异步httpclient时设置并发连接数
     */
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            return httpClientBuilder;
        });
    }

    public RestHighLevelClient getRhlClient() {
        return restHighLevelClient;
    }

    public void close() {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                log.error("error in shut down restHighLevelClient!");
            }
        }
        log.info("close restHighLevelClient!");
    }
}
