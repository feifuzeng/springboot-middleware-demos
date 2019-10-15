package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Open Index API
 * @createTime 2019年08月21日 13:56:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-open-index.html
 */
@Log4j2
public class OpenIndexDemo  extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void open() throws Exception {


        /**2. Open index Request*/
        OpenIndexRequest request = new OpenIndexRequest(Consts.INDEX_NAME);
        /** 可选参数*/
        request.timeout(TimeValue.timeValueMinutes(2));
        // or
//        request.timeout("2m");

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        //or
//        request.masterNodeTimeout("1m");

        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        OpenIndexResponse openIndexResponse = client.indices().open(request, RequestOptions.DEFAULT);

        boolean acknowledged = openIndexResponse.isAcknowledged();
        boolean shardsAcked = openIndexResponse.isShardsAcknowledged();

        log.info("打开索引:{}，acknowledged：{},shardsAcked:{}", Consts.INDEX_NAME, acknowledged,shardsAcked);

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousDelete(RestHighLevelClient client, DeleteIndexRequest request) throws Exception {
        AcknowledgedResponse response = new AcknowledgedResponse();
        try {
            response = client.indices().delete(request, RequestOptions.DEFAULT);
            return response;
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {
                log.error("index not found!");
            }
        }
        return response;
    }

}
