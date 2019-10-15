package com.github.feifuzeng.middleware.elasticsearch.document.singal;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Get API
 * @createTime 2019年08月22日 15:30:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-document-get.html
 */
@Log4j2
public class DocExistDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void exist() throws Exception {

        /** request*/
        GetRequest request = new GetRequest(
                Consts.INDEX_NAME,
                Consts.TYPE_NAME,
                "1");
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        /** execute*/
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        log.info("文档id为【{}】的文档是否存在：{}", request.id(), exists);
    }
}
