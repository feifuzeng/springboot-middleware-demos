package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.indexlifecycle.StartILMRequest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Start Index Lifecycle Management API
 * @createTime 2019年08月20日 17:40:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-start-ilm.html
 */
@Log4j2
public class StartLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    @Test
    public void start() throws Exception {

        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /**2. 请求*/
        StartILMRequest request = new StartILMRequest();
        ;
        /**3. 执行*/

        /**4. 同步执行*/
        AcknowledgedResponse response = synchronousStart(client, request);
        log.info("同步执行结果{}", response.isAcknowledged());

        /**4. 异步执行*/
//        aynchronousStart(client,request);


    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousStart(RestHighLevelClient client, StartILMRequest request) throws Exception {
        AcknowledgedResponse response = client.indexLifecycle()
                .startILM(request, RequestOptions.DEFAULT);
        return response;
    }

    /**
     * 异步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private void aynchronousStart(RestHighLevelClient client, StartILMRequest request) {
        ActionListener<AcknowledgedResponse> listener =
                new ActionListener<AcknowledgedResponse>() {
                    @Override
                    public void onResponse(AcknowledgedResponse response) {
                        boolean acknowledged = response.isAcknowledged();
                        log.info("异步执行结果{}", acknowledged);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("异步执行异常{}", e);
                    }
                };
        client.indexLifecycle().startILMAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
