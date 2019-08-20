package com.github.feifuzeng.middleware.elasticsearch.crud.template;

import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.entity.Person;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 基于ElasticSearchTemplate CRUD
 * @createTime 2019年08月19日 16:13:00
 */
@Log4j2
public class ElasticSearchTemplateTests extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert() {
        Person person = new Person();
        person.setName("诸葛亮");
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(person).build();
        String resule = elasticsearchTemplate.index(indexQuery);
        log.info(resule);
    }

    @Test
    public void testQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "诸葛亮"))
                .build();
        List<Person> list = elasticsearchTemplate.queryForList(searchQuery, Person.class);
        System.out.println(list);
    }
}
