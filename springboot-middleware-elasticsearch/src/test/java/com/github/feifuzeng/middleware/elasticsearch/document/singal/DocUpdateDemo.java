package com.github.feifuzeng.middleware.elasticsearch.document.singal;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Update API
 * @createTime 2019年08月22日 15:30:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-document-update.html
 */
@Log4j2
public class DocUpdateDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void update() throws Exception {

        /** request*/
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");

        UpdateRequest request = new UpdateRequest(
                Consts.INDEX_NAME,
                Consts.TYPE_NAME,
                "1").doc(jsonMap);

        /** execute*/
        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String type = updateResponse.getType();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = updateResponse.getShardInfo();
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
