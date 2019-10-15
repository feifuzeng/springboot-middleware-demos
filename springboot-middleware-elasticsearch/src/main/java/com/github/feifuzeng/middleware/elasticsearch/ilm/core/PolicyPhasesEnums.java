package com.github.feifuzeng.middleware.elasticsearch.ilm.core;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 索引生命周期阶段枚举
 * @createTime 2019年08月29日 17:24:00
 * @Reference https://www.elastic.co/guide/en/elasticsearch/reference/6.7/ilm-policy-definition.html
 */
public enum PolicyPhasesEnums {


    /**
     * 官网介绍：
     * hot:The index is actively being written to
     * warm:The index is generally not being written to, but is still queried
     * cold:The index is no longer being updated and is seldom queried. The information still needs to be searchable, but it’s okay if those queries are slower.
     * delete:The index is no longer needed and can safely be deleted
     */
    HOT("hot", "索引数据正在活跃的更新和查询"),
    WARM("warm", "索引数据不再被更新，但是仍被查询"),
    COLD("cold", "索引已经不被更新且很少查询。但是索引数据的信息还需要被搜索，若被搜索则比较慢"),
    DELETE("delete", "索引不再被需要可以安全的删除");

    private String name;

    private String desc;

    private PolicyPhasesEnums(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
