package com.github.feifuzeng.middleware.elasticsearch.crud.rest;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author feifz
 * @version 1.0.0
 * @Description elasticsearch-rest-high-level-client入门示例
 * @createTime 2019年08月19日 17:01:00
 */
@Log4j2
public class ElasticSearchRestHighLevelClientTests extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    private static final String  ELASTICSEARCH_INDEX_NAME = "testindex";

    private static final String ELASTICSEARCH_TYPE_NAME = "doc";

    @Test
    public void test()throws Exception {
        RestHighLevelClient client = elacticSearchConfig.initClient();
        log.info(client.indices());
        client.close();
    }

    /**
     * 创建员工信息
     * @throws Exception
     */
    @Test
    public  void createEmployee() throws Exception {
        RestHighLevelClient client = elacticSearchConfig.initClient();
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("name", "jack");
            builder.field("age", 27);
            builder.field("position", "technique");
            builder.field("country", "china");
            builder.field("join_date", "2017-01-01");
            builder.field("salary", 10000);
        }
        builder.endObject();
        IndexRequest request = new IndexRequest(ELASTICSEARCH_INDEX_NAME).type(ELASTICSEARCH_TYPE_NAME).id("1").source(builder);
        IndexResponse indexResponse =  client.index(request, RequestOptions.DEFAULT);
        log.info(indexResponse.getResult());
        client.close();
    }

    /**
     * 获取员工信息
     */
    @Test
    public void getEmployee() throws IOException {
        RestHighLevelClient client = elacticSearchConfig.initClient();
        GetRequest request = new GetRequest(ELASTICSEARCH_INDEX_NAME, ELASTICSEARCH_TYPE_NAME,"1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        client.close();
    }

    /**
     * 修改员工信息
     */
    @Test
    public void updateEmployee() throws IOException {
        RestHighLevelClient client = elacticSearchConfig.initClient();
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("position", "wewe");
        }
        builder.endObject();
        UpdateRequest request = new UpdateRequest(ELASTICSEARCH_INDEX_NAME,ELASTICSEARCH_TYPE_NAME, "1").doc(builder);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        client.close();
    }

    /**
     * 删除员工信息
     */
    @Test
    public  void deleteEmployee() throws IOException {
        RestHighLevelClient client = elacticSearchConfig.initClient();
        DeleteRequest request = new DeleteRequest(ELASTICSEARCH_INDEX_NAME,ELASTICSEARCH_TYPE_NAME, "1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        client.close();
    }
}
