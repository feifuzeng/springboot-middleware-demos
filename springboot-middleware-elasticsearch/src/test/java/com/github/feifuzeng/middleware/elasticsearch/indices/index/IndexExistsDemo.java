package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Indices Exists API
 * @createTime 2019年08月20日 20:37:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-indices-exists.html
 */
@Log4j2
public class IndexExistsDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void exist() throws Exception {

        GetIndexRequest request = new GetIndexRequest(Consts.INDEX_NAME);

        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        log.info("索引:{}是否存在：{}", Consts.INDEX_NAME, exists);
    }
}
