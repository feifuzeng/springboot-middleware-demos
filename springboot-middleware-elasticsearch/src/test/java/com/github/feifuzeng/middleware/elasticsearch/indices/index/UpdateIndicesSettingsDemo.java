package com.github.feifuzeng.middleware.elasticsearch.indices.index;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Update Indices Settings API
 * @createTime 2019年08月21日 11:20:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-indices-put-settings.html
 */
@Log4j2
public class UpdateIndicesSettingsDemo  extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void update()throws Exception{

        /** 1.请求*/
        /**Update settings for one index*/
        UpdateSettingsRequest request = new UpdateSettingsRequest(Consts.INDEX_NAME);
        /**Update settings for multiple indices*/
//        UpdateSettingsRequest requestMultiple = new UpdateSettingsRequest("index1", "index2");
        /**Update settings for all indices*/
//        UpdateSettingsRequest requestAll = new UpdateSettingsRequest();

        Settings.Builder settingsBuilder =
                Settings.builder()
//                        .put("index.number_of_shards", 3)
//                        .put("index.number_of_replicas", 2)
                        .put("index.lifecycle.name", Consts.POLICY_NAME)
                        .put("index.lifecycle.rollover_alias", Consts.INDEX_LIFECYCLE_ROLLOVER_ALIAS);
        request.settings(settingsBuilder);
        request.setPreserveExisting(true);

        /** 2.响应*/
        AcknowledgedResponse response = synchronousUpdate(client,request);
        log.info("索引:{}更新配置成功？：{}", Consts.INDEX_NAME, response.isAcknowledged());
    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private AcknowledgedResponse synchronousUpdate(RestHighLevelClient client, UpdateSettingsRequest request) throws Exception {
        AcknowledgedResponse updateSettingsResponse =
                client.indices().putSettings(request, RequestOptions.DEFAULT);
        return updateSettingsResponse;
    }

}
