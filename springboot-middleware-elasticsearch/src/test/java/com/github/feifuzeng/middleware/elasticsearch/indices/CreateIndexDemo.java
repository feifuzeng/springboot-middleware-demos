package com.github.feifuzeng.middleware.elasticsearch.indices;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Create Index API
 * @createTime 2019年08月20日 19:47:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-create-index.html
 */
@Log4j2
public class CreateIndexDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;


    @Test
    public void create() throws Exception {

        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /** 2. Create Index Request*/
        CreateIndexRequest request = new CreateIndexRequest(Consts.INDEX_NAME);


        /** 3. Index settings*/
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
                .put("index.lifecycle.name", Consts.POLICY_NAME)
                .put("index.lifecycle.rollover_alias", Consts.INDEX_LIFECYCLE_ROLLOVER_ALIAS)
        );

        /** Index mappings*/
        /*// 方式1
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"message\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);*/

        // 方式2
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);

        /* //方式3
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("message");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(Consts.TYPE_NAME,builder);
        */

        /** 执行*/
        /** 同步执行*/
        CreateIndexResponse createIndexResponse = synchronousCreate(client, request);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        log.info("创建索引{},结果->acknowledged:{},shardsAcknowledged:{}", Consts.INDEX_NAME, acknowledged, shardsAcknowledged);

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private CreateIndexResponse synchronousCreate(RestHighLevelClient client, CreateIndexRequest request) throws Exception {
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse;
    }


}
