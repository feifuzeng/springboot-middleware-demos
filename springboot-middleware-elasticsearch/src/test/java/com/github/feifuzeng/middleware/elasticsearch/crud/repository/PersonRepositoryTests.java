package com.github.feifuzeng.middleware.elasticsearch.crud.repository;

import com.alibaba.fastjson.JSON;
import com.github.feifuzeng.middleware.elasticsearch.SpringbootMiddlewareElasticsearchApplicationTests;
import com.github.feifuzeng.middleware.elasticsearch.entity.Person;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.DateUtil;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 测试 Repository 操作ES
 * @createTime 2019年08月19日 14:54:00
 */
@Log4j2
public class PersonRepositoryTests extends SpringbootMiddlewareElasticsearchApplicationTests {

    @Autowired
    private PersonRepository repo;


    /**
     * 测试新增
     */
    @Test
    public void save() {
        Person person = new Person(1L, "刘备", "蜀国", 18, DateUtil.parse("1990-01-02 03:04:05"), "刘备（161年－223年6月10日），即汉昭烈帝（221年－223年在位）");
        Person save = repo.save(person);
        log.info("【save】= {}", save);
    }

    /**
     * 测试批量新增
     */
    @Test
    public void saveList() {
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person(2L, "曹操", "魏国", 20, DateUtil.parse("1988-01-02 03:04:05"), "曹操（155年－220年3月15日），字孟德，一名吉利，小字阿瞒，沛国谯县（今安徽亳州）人。"));
        personList.add(new Person(3L, "孙权", "吴国", 19, DateUtil.parse("1989-01-02 03:04:05"), "孙权（182年－252年5月21日），字仲谋，吴郡富春（今浙江杭州富阳区）人。三国时代孙吴的建立者（229年－252年在位）。"));
        personList.add(new Person(4L, "诸葛亮", "蜀国", 16, DateUtil.parse("1992-01-02 03:04:05"), "诸葛亮（181年-234年10月8日），字孔明，号卧龙，徐州琅琊阳都（今山东临沂市沂南县）人，三国时期蜀国丞相，杰出的政治家、军事家、外交家、文学家、书法家、发明家。"));
        Iterable<Person> people = repo.saveAll(personList);
        log.info("【people】= {}", people);
    }

    /**
     * 测试更新
     */
    @Test
    public void update() {
        repo.findById(1L).ifPresent(person -> {
            person.setRemark(person.getRemark() + "更新更新更新更新更新");
            Person save = repo.save(person);
            log.info("【save】= {}", save);
        });
    }

    /**
     * 测试删除
     */
    @Test
    public void delete() {
        // 主键删除
        repo.deleteById(1L);

        // 对象删除
        repo.findById(2L).ifPresent(person -> repo.delete(person));

        // 批量删除
        repo.deleteAll(repo.findAll());
    }

    /**
     * 测试普通查询，按生日倒序
     */
    @Test
    public void select() {
        repo.findAll(Sort.by(Sort.Direction.DESC, "birthday"))
                .forEach(person -> log.info("{} 生日: {}", person.getName(), person.getBirthday()));
    }

    /**
     * 自定义查询，根据年龄范围查询
     */
    @Test
    public void customSelectRangeOfAge() {
        repo.findByAgeBetween(18, 19).forEach(person -> log.info("{} 年龄: {}", person.getName(), person.getAge()));
    }

    /**
     * 高级查询
     */
    @Test
    public void advanceSelect() {
        // QueryBuilders 提供了很多静态方法，可以实现大部分查询条件的封装
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "孙权");
        log.info("【queryBuilder】= {}", queryBuilder.toString());
        repo.search(queryBuilder).forEach(person -> log.info("【person】= {}", person));
    }

    /**
     * 自定义高级查询
     */
    @Test
    public void customAdvanceSelect() {
        // 构造查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("remark", "东汉"));
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", "诸葛亮"));
        // 排序条件
        queryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));
        // 分页条件
        queryBuilder.withPageable(PageRequest.of(0, 2));
        Page<Person> people = repo.search(queryBuilder.build());
        log.info("【people】总条数 = {}", people.getTotalElements());
        log.info("【people】总页数 = {}", people.getTotalPages());
        people.forEach(person -> log.info("【person】= {}，年龄 = {}", person.getName(), person.getAge()));
    }

    /**
     * 测试聚合，测试平均年龄
     */
    @Test
    public void agg() {
        // 构造查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));

        // 平均年龄
        queryBuilder.addAggregation(AggregationBuilders.avg("avg").field("age"));

        log.info("【queryBuilder】= {}", JSON.toJSON(queryBuilder.build()));

        AggregatedPage<Person> people = (AggregatedPage<Person>) repo.search(queryBuilder.build());
        double avgAge = ((InternalAvg) people.getAggregation("avg")).getValue();
        log.info("【avgAge】= {}", avgAge);
    }

    /**
     * 测试高级聚合查询，每个国家的人有几个，每个国家的平均年龄是多少
     */
    @Test
    public void advanceAgg() {
        // 构造查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));

        // 1. 添加一个新的聚合，聚合类型为terms，聚合名称为country，聚合字段为age
        queryBuilder.addAggregation(AggregationBuilders.terms("country").field("country")
                // 2. 在国家聚合桶内进行嵌套聚合，求平均年龄
                .subAggregation(AggregationBuilders.avg("avg").field("age")));

        log.info("【queryBuilder】= {}", JSON.toJSON(queryBuilder.build()));

        // 3. 查询
        AggregatedPage<Person> people = (AggregatedPage<Person>) repo.search(queryBuilder.build());

        // 4. 解析
        // 4.1. 从结果中取出名为 country 的那个聚合，因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms country = (StringTerms) people.getAggregation("country");
        // 4.2. 获取桶
        List<StringTerms.Bucket> buckets = country.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            // 4.3. 获取桶中的key，即国家名称  4.4. 获取桶中的文档数量
            log.info("{} 总共有 {} 人", bucket.getKeyAsString(), bucket.getDocCount());
            // 4.5. 获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("avg");
            log.info("平均年龄：{}", avg);
        }
    }
}
