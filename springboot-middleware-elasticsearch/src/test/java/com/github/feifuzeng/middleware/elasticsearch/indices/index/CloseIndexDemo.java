package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Open Index API
 * @createTime 2019年08月21日 13:56:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-close-index.html
 */
@Log4j2
public class CloseIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void close() throws Exception {


        /**2. Close index Request*/
        CloseIndexRequest request = new CloseIndexRequest(Consts.INDEX_NAME);
        /** 可选参数*/
        request.timeout(TimeValue.timeValueMinutes(2));

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));

        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        AcknowledgedResponse response = synchronousClose(client, request);
        log.info("索引:{}关闭成功？：{}", Consts.INDEX_NAME, response.isAcknowledged());

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousClose(RestHighLevelClient client, CloseIndexRequest request) throws Exception {
        AcknowledgedResponse response = client.indices().close(request, RequestOptions.DEFAULT);
        return response;
    }

}
