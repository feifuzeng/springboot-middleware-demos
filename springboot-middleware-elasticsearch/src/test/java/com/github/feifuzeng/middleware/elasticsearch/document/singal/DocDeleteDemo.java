package com.github.feifuzeng.middleware.elasticsearch.document.singal;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Delete API
 * @createTime 2019年08月22日 15:30:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-document-delete.html
 */
@Log4j2
public class DocDeleteDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void exist() throws Exception {

        /** request*/
        DeleteRequest request = new DeleteRequest(
                Consts.INDEX_NAME,
                Consts.TYPE_NAME,
                "1");

        /** execute*/
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                log.error("失败原因为：{}", reason);
            }
        }
        log.info("execute delete doc successful ,info-->index:{},type:{},id:{},version:{}", index, type, id, version);
    }
}
