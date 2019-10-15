package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indexlifecycle.GetLifecyclePolicyRequest;
import org.elasticsearch.client.indexlifecycle.GetLifecyclePolicyResponse;
import org.elasticsearch.client.indexlifecycle.LifecyclePolicyMetadata;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Get Lifecycle Policy API
 * @createTime 2019年08月20日 15:36:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-get-lifecycle-policy.html
 */
@Log4j2
public class GetLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void get() throws Exception {

        /**2. 请求*/
        GetLifecyclePolicyRequest request =
                new GetLifecyclePolicyRequest(Consts.POLICY_NAME);
        /**3. 执行*/

        /**4. 同步执行*/
        GetLifecyclePolicyResponse response = synchronousGet(client, request);
        ImmutableOpenMap<String, LifecyclePolicyMetadata> policies =
                response.getPolicies();
        LifecyclePolicyMetadata myPolicyMetadata =
                policies.get(Consts.POLICY_NAME);
        String myPolicyName = myPolicyMetadata.getName();
        long version = myPolicyMetadata.getVersion();
        String lastModified = myPolicyMetadata.getModifiedDateString();
        log.info("同步执行结果-->名称：{}，版本:{},上次修改时间：{}", myPolicyName, version, lastModified);
        /**4. 异步执行*/
//        aynchronousGet(client,request);


    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private GetLifecyclePolicyResponse synchronousGet(RestHighLevelClient client, GetLifecyclePolicyRequest request) throws Exception {
        GetLifecyclePolicyResponse response = client.indexLifecycle()
                .getLifecyclePolicy(request, RequestOptions.DEFAULT);
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
    private void aynchronousGet(RestHighLevelClient client, GetLifecyclePolicyRequest request) {
        ActionListener<GetLifecyclePolicyResponse> listener =
                new ActionListener<GetLifecyclePolicyResponse>() {
                    @Override
                    public void onResponse(GetLifecyclePolicyResponse response) {
                        ImmutableOpenMap<String, LifecyclePolicyMetadata>
                                policies = response.getPolicies();
                        log.info("异步执行结果{}", policies);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("异步执行异常{}", e);
                    }
                };
        client.indexLifecycle().getLifecyclePolicyAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
