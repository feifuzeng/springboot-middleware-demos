package com.github.feifuzeng.middleware.elasticsearch.util;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 常量定义
 * @createTime 2019年08月19日 14:48:00
 */
public class Consts {

    /**
     * 索引名称
     */
    public static final String INDEX_NAME = "person";

    /**
     * 类型名称
     */
    public static final String TYPE_NAME = "doc";

    /**
     * 索引安全策略名
     */
    public static final String POLICY_NAME = "watch-history-ilm-policy";

    /**
     * 索引别名
     */
    public static final String INDEX_LIFECYCLE_ROLLOVER_ALIAS = "my_policy_alias";

    /**
     * 字段1
     */
    public static final String TEST_FIELD_ONE = "message";

    /**
     * 字段2
     */
    public static final String TEST_FIELD_TWO = "name";

    /**
     * 字段3
     */
    public static final String TEST_FIELD_THREE = "age";
}
