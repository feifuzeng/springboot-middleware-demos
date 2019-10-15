package com.github.feifuzeng.middleware.elasticsearch._cat.indies;

import com.alibaba.fastjson.JSON;
import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch._cat.indies.base.CatIndexInfo;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.core.ElasticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.RestTemplateUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description index相关api
 * @createTime 2019年08月29日 14:44:00
 */
@Log4j2
@EnableAutoConfiguration
public class CatIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {


    @Autowired
    private ElasticSearchConfig elasticSearchConfig;

    @Test
    public void test() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String host = elasticSearchConfig.getHost();
        int port = elasticSearchConfig.getPort();
        port = 19200;
        String esUrl = "http://" + host + ":" + port + "/_cat/indices?v=&s=index&format=json";
        Object catIndexObject = restTemplate.withBasicAuth(
                elasticSearchConfig.getUsername(), elasticSearchConfig.getPassword()).getForEntity(esUrl, Object.class).getBody();
        log.info(catIndexObject.toString());
        List<CatIndexInfo> catIndexInfos = JSON.parseArray(JSON.toJSONString(catIndexObject), CatIndexInfo.class);
        log.info(catIndexInfos.size());
    }

    @Test
    public void testIndex() {
        String host = elasticSearchConfig.getHost();
        int port = elasticSearchConfig.getPort();
        String esUrl = "http://" + host + ":" + port + "/_cat/indices/sg6*?v=&s=index&format=json";
        RestTemplate template = new RestTemplate();
        template = RestTemplateUtils.auth(template, elasticSearchConfig.getUsername(), elasticSearchConfig.getPassword());
        Object resultObj = template.getForEntity(esUrl, Object.class).getBody();
        log.info(resultObj.toString());
        List<CatIndexInfo> catIndexInfos = JSON.parseArray(JSON.toJSONString(resultObj), CatIndexInfo.class);
        log.info(catIndexInfos.size());
    }

}
