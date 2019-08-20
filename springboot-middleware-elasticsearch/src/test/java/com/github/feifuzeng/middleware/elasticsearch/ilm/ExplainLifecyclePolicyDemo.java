package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indexlifecycle.ExplainLifecycleRequest;
import org.elasticsearch.client.indexlifecycle.ExplainLifecycleResponse;
import org.elasticsearch.client.indexlifecycle.IndexLifecycleExplainResponse;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Explain Lifecycle API
 * @createTime 2019年08月20日 15:53:00
 * Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-explain-lifecycle.html
 */
@Log4j2
public class ExplainLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    @Test
    public void explain() throws Exception {
        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /**2. 请求*/
        ExplainLifecycleRequest request =
                new ExplainLifecycleRequest(Consts.INDEX_NAME);
        /**3. 执行*/

        /**4. 同步执行*/
        ExplainLifecycleResponse response = synchronousExplain(client,request);

        Map<String, IndexLifecycleExplainResponse> indices =
                response.getIndexResponses();
        IndexLifecycleExplainResponse myIndex = indices.get(Consts.INDEX_NAME);
        String policyName = myIndex.getPolicyName();
        boolean isManaged = myIndex.managedByILM();
        if(!isManaged){
            log.info("{}没有配置索引策略", Consts.INDEX_NAME);
            return;
        }
        String phase = myIndex.getPhase();
        long phaseTime = myIndex.getPhaseTime();
        String action = myIndex.getAction();
        long actionTime = myIndex.getActionTime();
        String step = myIndex.getStep();
        long stepTime = myIndex.getStepTime();

        String failedStep = myIndex.getFailedStep();
        log.info("{}索引策略信息如下-->policyName:{},phase:{},step:{}", Consts.INDEX_NAME,policyName,phase,step);

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
    private ExplainLifecycleResponse synchronousExplain(RestHighLevelClient client, ExplainLifecycleRequest request) throws Exception {
        ExplainLifecycleResponse response = client.indexLifecycle()
                .explainLifecycle(request, RequestOptions.DEFAULT);
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
    private void aynchronousExplain(RestHighLevelClient client, ExplainLifecycleRequest request) {
        ActionListener<ExplainLifecycleResponse> listener =
                new ActionListener<ExplainLifecycleResponse>() {
                    @Override
                    public void onResponse(ExplainLifecycleResponse response) {
                        Map<String, IndexLifecycleExplainResponse> indices =
                                response.getIndexResponses();
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                };
        client.indexLifecycle().explainLifecycleAsync(request,
                RequestOptions.DEFAULT, listener);
    }
}
