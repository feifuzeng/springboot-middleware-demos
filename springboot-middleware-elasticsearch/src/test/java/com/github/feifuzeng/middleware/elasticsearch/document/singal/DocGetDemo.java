package com.github.feifuzeng.middleware.elasticsearch.document.singal;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Get API
 * @createTime 2019年08月22日 15:30:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-document-get.html
 */
@Log4j2
public class DocGetDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void get() throws Exception {

        /** request*/
        GetRequest request = new GetRequest(
                Consts.INDEX_NAME,
                Consts.TYPE_NAME,
                "1");

        /** Optional arguments */
        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);

        /** execute*/
        GetResponse getResponse = synchronousGet(client, request);
        /** response*/
        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
            log.info("the result is :{}", sourceAsMap.toString());
        } else {

        }

    }

    /**
     * 同步执行
     *
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private GetResponse synchronousGet(RestHighLevelClient client, GetRequest request) throws Exception {
        GetResponse response = null;
        try {
            response = client.get(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                log.error("doc not found error in execute...");
            }
        }
        return response;
    }
}
