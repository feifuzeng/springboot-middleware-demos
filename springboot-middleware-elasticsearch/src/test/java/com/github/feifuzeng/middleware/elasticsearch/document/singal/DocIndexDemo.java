package com.github.feifuzeng.middleware.elasticsearch.document.singal;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Index API
 * @createTime 2019年08月22日 15:30:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-document-index.html
 */
@Log4j2
public class DocIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void index() throws Exception {

        /** request*/

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(Consts.TEST_FIELD_ONE, "测试数据");
        jsonMap.put(Consts.TEST_FIELD_TWO, "Mary");
        jsonMap.put(Consts.TEST_FIELD_THREE, 18);
        IndexRequest request = new IndexRequest(Consts.INDEX_NAME, Consts.TYPE_NAME, "1")
                .source(jsonMap);

        /** Optional arguments */
//        request.routing("routing");
//        request.parent("parent");
//        request.timeout(TimeValue.timeValueSeconds(1));
//        request.timeout("1s");

        /** execute*/
        IndexResponse indexResponse = synchronousIndex(client, request);
        if (indexResponse == null) {
            log.error("执行异常");
            return;
        }

        /** response*/

        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("<<<CREATED>>>>execute successful ,info-->index:{},type:{},id:{},version:{}", index, type, id, version);
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("<<<UPDATED>>>>execute successful ,info-->index:{},type:{},id:{},version:{}", index, type, id, version);
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                log.info("{} 执行失败，失败原因：{}", failure.shardId(), reason);
            }
        }

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private IndexResponse synchronousIndex(RestHighLevelClient client, IndexRequest request) throws Exception {
        IndexResponse response = null;
        try {
            response = client.index(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                log.error("error in execute :{}", e);
            }
        } catch (Exception e) {
            log.error("error in execute------> :{}", e);
        }
        return response;
    }
}
