package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indexlifecycle.RemoveIndexLifecyclePolicyRequest;
import org.elasticsearch.client.indexlifecycle.RemoveIndexLifecyclePolicyResponse;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Remove Policy from Index API
 * @createTime 2019年08月20日 19:27:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-remove-lifecycle-policy-from-index.html
 */
@Log4j2
public class RemoveLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    @Test
    public void remove() throws Exception {

        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /**2. 请求*/
        List<String> indices = new ArrayList<>();
        indices.add(Consts.INDEX_NAME);
        RemoveIndexLifecyclePolicyRequest request =
                new RemoveIndexLifecyclePolicyRequest(indices);
        ;
        /**3. 执行*/

        /**3.1. 同步执行*/
        RemoveIndexLifecyclePolicyResponse response = synchronousRemove(client, request);
        boolean hasFailures = response.hasFailures();
        List<String> failedIndexes = response.getFailedIndexes();
        log.info("是否有失败：{}，失败索引数量为：{}", hasFailures, failedIndexes.size());
        /**3.2. 异步执行*/
//        aynchronousRemove(client,request);
    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private RemoveIndexLifecyclePolicyResponse synchronousRemove(RestHighLevelClient client, RemoveIndexLifecyclePolicyRequest request) throws Exception {
        RemoveIndexLifecyclePolicyResponse response = client
                .indexLifecycle()
                .removeIndexLifecyclePolicy(request, RequestOptions.DEFAULT);
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
    private void aynchronousRemove(RestHighLevelClient client, RemoveIndexLifecyclePolicyRequest request) {
        ActionListener<RemoveIndexLifecyclePolicyResponse> listener =
                new ActionListener<RemoveIndexLifecyclePolicyResponse>() {
                    @Override
                    public void onResponse(RemoveIndexLifecyclePolicyResponse response) {
                        boolean hasFailures = response.hasFailures();
                        List<String> failedIndexes = response.getFailedIndexes();
                        log.info("是否有失败：{}，失败索引数量为：{}", hasFailures, failedIndexes.size());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("异步执行异常{}", e);
                    }
                };
        client.indexLifecycle().removeIndexLifecyclePolicyAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
