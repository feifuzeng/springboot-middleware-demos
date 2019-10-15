package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.indexlifecycle.DeleteLifecyclePolicyRequest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Delete Lifecycle Policy API
 * @createTime 2019年08月20日 15:04:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-delete-lifecycle-policy.html
 */
@Log4j2
public class DeleteLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void delete() throws Exception {

        /**2. 请求*/
        DeleteLifecyclePolicyRequest request =
                new DeleteLifecyclePolicyRequest(Consts.POLICY_NAME);
        /**3. 执行*/

        /**4. 同步执行*/
        AcknowledgedResponse response = synchronousDelete(client, request);
        log.info("同步执行结果{}", response.isAcknowledged());

        /**4. 异步执行*/
//        aynchronousDelete(client,request);


    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousDelete(RestHighLevelClient client, DeleteLifecyclePolicyRequest request) throws Exception {
        AcknowledgedResponse response = client.indexLifecycle()
                .deleteLifecyclePolicy(request, RequestOptions.DEFAULT);
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
    private void aynchronousDelete(RestHighLevelClient client, DeleteLifecyclePolicyRequest request) {
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
        client.indexLifecycle().deleteLifecyclePolicyAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
