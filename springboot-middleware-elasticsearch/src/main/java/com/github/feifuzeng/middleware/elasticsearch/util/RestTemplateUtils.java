package com.github.feifuzeng.middleware.elasticsearch.util;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author feifz
 * @version 1.0.0
 * @Description RestTemplate相关工具类
 * @createTime 2019年08月29日 17:07:00
 */
public class RestTemplateUtils {

    /**
     * 添加授权信息
     * @param template
     * @param username
     * @param password
     * @return
     */
    public static RestTemplate auth(RestTemplate template, String username, String password) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        provider.setCredentials(AuthScope.ANY, credentials);
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
                .useSystemProperties().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        template.setRequestFactory(requestFactory);
        return template;
    }


}
