package com.github.feifuzeng.middleware.elasticsearch.search;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description Search API
 * @createTime 2019年08月23日 09:36:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-search.html
 */
@Log4j2
public class SearchApiDemo extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void search() throws Exception {

        /** 可指定索引查询，不指定从全部索引里面查找*/
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(Consts.INDEX_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        sourceBuilder.sort(new FieldSortBuilder(Consts.TEST_FIELD_THREE).order(SortOrder.ASC));


        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        log.info(searchResponse.toString());


    }

}
