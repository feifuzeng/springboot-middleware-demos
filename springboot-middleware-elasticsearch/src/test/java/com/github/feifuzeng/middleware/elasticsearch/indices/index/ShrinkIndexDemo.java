package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.admin.indices.shrink.ResizeRequest;
import org.elasticsearch.action.admin.indices.shrink.ResizeResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Shrink index API
 * @createTime 2019年08月21日 13:56:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-shrink-index.html
 */
@Log4j2
public class ShrinkIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void close() throws Exception {


        /**2. Shrink index Request*/
        ResizeRequest request = new ResizeRequest("target_index", "source_index");
        /** 可选参数*/

        AcknowledgedResponse response = synchronousShrink(client, request);
        log.info("Shrink索引:{}成功？：{}", Consts.INDEX_NAME, response.isAcknowledged());

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private ResizeResponse synchronousShrink(RestHighLevelClient client, ResizeRequest request) throws Exception {
        ResizeResponse response = client.indices().shrink(request, RequestOptions.DEFAULT);
        return response;
    }

}
