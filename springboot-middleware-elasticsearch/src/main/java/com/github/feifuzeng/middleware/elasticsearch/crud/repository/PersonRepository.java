package com.github.feifuzeng.middleware.elasticsearch.crud.repository;

import com.github.feifuzeng.middleware.elasticsearch.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 用户持久层
 * @createTime 2019年08月19日 14:52:00
 */
public interface PersonRepository extends ElasticsearchRepository<Person, Long> {

    /**
     * 根据年龄区间查询
     *
     * @param min 最小值
     * @param max 最大值
     * @return 满足条件的用户列表
     */
    List<Person> findByAgeBetween(Integer min, Integer max);
}
