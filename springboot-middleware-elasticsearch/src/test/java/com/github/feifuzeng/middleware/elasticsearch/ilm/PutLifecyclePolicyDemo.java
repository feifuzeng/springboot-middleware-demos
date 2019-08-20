package com.github.feifuzeng.middleware.elasticsearch.ilm;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.crud.rest.ElacticSearchConfig;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.indexlifecycle.DeleteAction;
import org.elasticsearch.client.indexlifecycle.LifecycleAction;
import org.elasticsearch.client.indexlifecycle.LifecyclePolicy;
import org.elasticsearch.client.indexlifecycle.Phase;
import org.elasticsearch.client.indexlifecycle.PutLifecyclePolicyRequest;
import org.elasticsearch.client.indexlifecycle.RolloverAction;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Put Lifecycle Policy API
 * @createTime 2019年08月20日 14:27:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-put-lifecycle-policy.html
 */
@Log4j2
public class PutLifecyclePolicyDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private ElacticSearchConfig elacticSearchConfig;

    @Test
    public void put() throws Exception{

        /**1. 初始化 client*/
        RestHighLevelClient client = elacticSearchConfig.initClient();

        /**2. 请求*/
        Map<String, Phase> phases = new HashMap<>();
        Map<String, LifecycleAction> hotActions = new HashMap<>();
        hotActions.put(RolloverAction.NAME, new RolloverAction(
                new ByteSizeValue(50, ByteSizeUnit.GB), new TimeValue(10,TimeUnit.HOURS), 2L));
        /** Adds a hot phase with a rollover action */
        phases.put("hot", new Phase("hot", TimeValue.ZERO, hotActions));

        Map<String, LifecycleAction> deleteActions =
                Collections.singletonMap(DeleteAction.NAME, new DeleteAction());
        phases.put("delete", new Phase("delete",
        /** Adds a delete phase that will delete in the index 90 days after rollover */
                new TimeValue(90, TimeUnit.DAYS), deleteActions));

        /** Creates the policy with the defined phases and the name my_policy */
        LifecyclePolicy policy = new LifecyclePolicy(Consts.POLICY_NAME, phases);
        PutLifecyclePolicyRequest request =
                new PutLifecyclePolicyRequest(policy);
        /**3. 执行*/

        /**4. 同步执行*/
        AcknowledgedResponse response = synchronousPut(client,request);
        log.info("同步执行结果{}",response.isAcknowledged());

//        /**4. 异步执行*/
//        aynchronousPut(client,request);
    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousPut(RestHighLevelClient client, PutLifecyclePolicyRequest request) throws Exception {
        AcknowledgedResponse response = client.indexLifecycle().
                putLifecyclePolicy(request, RequestOptions.DEFAULT);
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
    private void aynchronousPut(RestHighLevelClient client, PutLifecyclePolicyRequest request) {
        ActionListener<AcknowledgedResponse> listener =
                new ActionListener<AcknowledgedResponse>() {
                    @Override
                    public void onResponse(AcknowledgedResponse response) {
                        boolean acknowledged = response.isAcknowledged();
                        log.info("同步执行结果{}",acknowledged);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("异步执行异常{}",e);
                    }
                };

        client.indexLifecycle().putLifecyclePolicyAsync(request,
                RequestOptions.DEFAULT, listener);
    }

}
