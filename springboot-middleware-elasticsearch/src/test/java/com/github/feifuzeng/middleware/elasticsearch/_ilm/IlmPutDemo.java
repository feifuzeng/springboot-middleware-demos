package com.github.feifuzeng.middleware.elasticsearch._ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.core.ElasticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年08月29日 19:02:00
 */
@Log4j2
public class IlmPutDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Autowired
    private ElasticSearchConfig elasticSearchConfig;

    @Test
    public void put() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String host = elasticSearchConfig.getHost();
        int port = elasticSearchConfig.getPort();
        String esUrl = "http://" + host + ":" + port + "/_ilm/policy"+ Consts.POLICY_NAME;
        restTemplate.withBasicAuth(
                elasticSearchConfig.getUsername(), elasticSearchConfig.getPassword()).put(esUrl, Object.class);
    }
}
