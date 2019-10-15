package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Get Index API
 * @createTime 2019年08月28日 16:56:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-get-index.html
 */
@Log4j2
public class GetIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;


    @Test
    public void get() throws Exception {
        GetIndexRequest request = new GetIndexRequest(".*");
        GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
        log.info("----------"+getIndexResponse);


    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
//    private AcknowledgedResponse synchronousDelete(RestHighLevelClient client, GetIndexRequest request) throws Exception {
//
//    }
}