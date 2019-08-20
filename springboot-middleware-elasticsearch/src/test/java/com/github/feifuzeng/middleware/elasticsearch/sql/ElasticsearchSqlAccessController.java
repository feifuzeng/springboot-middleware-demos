//package com.github.feifuzeng.middleware.elasticsearch.sql;
//
//import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.Resource;
//
///**
// * @author feifz
// * @version 1.0.0
// * @Description sql模式获取ElasticSearch数据
// * @createTime 2019年08月20日 10:33:00
// */
//@RestController
//@RequestMapping("/rest/elasticsearch/sql")
//public class ElasticsearchSqlAccessController {
//
//    @Resource
//    private RestTemplate restTemplate;
//
//    @Resource
//    private ElacticSearchConfig elacticSearchConfig;
//
//    @RequestMapping("query")
//    public Object access(String sql) {
//        Object object = restTemplate.getForEntity(elacticSearchConfig.getHost() + "//" + elacticSearchConfig.getPort(), Object.class);
//        return object;
//    }
//}
