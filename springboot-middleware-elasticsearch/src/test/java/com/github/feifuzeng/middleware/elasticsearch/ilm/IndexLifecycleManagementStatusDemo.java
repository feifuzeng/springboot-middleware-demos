package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.indexlifecycle.LifecycleManagementStatusRequest;
import org.elasticsearch.client.indexlifecycle.LifecycleManagementStatusResponse;
import org.elasticsearch.client.indexlifecycle.OperationMode;
import org.elasticsearch.client.indexlifecycle.StartILMRequest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Index Lifecycle Management Status API
 * @createTime 2019年08月20日 17:47:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-status.html
 */
@Log4j2
public class IndexLifecycleManagementStatusDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    @Test
    public void status() throws Exception{

        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /**2. 请求*/
        LifecycleManagementStatusRequest request =
                new LifecycleManagementStatusRequest();
        ;
        /**3. 执行*/

        /**4. 同步执行*/
        LifecycleManagementStatusResponse response = synchronousStatus(client, request);
        OperationMode operationMode = response.getOperationMode();
        log.info("同步执行结果{}", operationMode.name());

        /**4. 异步执行*/
//        aynchronousStatus(client,request);


    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private LifecycleManagementStatusResponse synchronousStatus(RestHighLevelClient client, LifecycleManagementStatusRequest request) throws Exception {
        LifecycleManagementStatusResponse response =
                client.indexLifecycle()
                        .lifecycleManagementStatus(request, RequestOptions.DEFAULT);
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
    private void aynchronousStatus(RestHighLevelClient client, LifecycleManagementStatusRequest request) {
        ActionListener<LifecycleManagementStatusResponse> listener =
                new ActionListener<LifecycleManagementStatusResponse>() {
                    @Override
                    public void onResponse(LifecycleManagementStatusResponse response) {
                        OperationMode operationMode = response
                                .getOperationMode();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("异步执行异常{}", e);
                    }
                };
        client.indexLifecycle().lifecycleManagementStatusAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
