package com.github.feifuzeng.middleware.elasticsearch._cat.indies.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 通过_cat api方式获取到的索引信息
 * @createTime 2019年08月29日 15:26:00
 */
@Data
public class CatIndexInfo {

    private String health;

    private String status;

    private String index;

    private String uuid;

    private String pri;

    private String rep;

    @JSONField(name = "docs.count")
    private String docsCount;

    @JSONField(name = "docs.deleted")
    private String docsDeleted;

    @JSONField(name = "store.size")
    private String storeSize;

    @JSONField(name = "pri.store.size")
    private String priStoreSize;
}
