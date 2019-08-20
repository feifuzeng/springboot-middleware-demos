package com.github.feifuzeng.middleware.elasticsearch.entity;

import com.github.feifuzeng.middleware.elasticsearch.util.Consts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 用户实体类
 * @createTime 2019年08月19日 14:50:00
 */
@Document(indexName = Consts.INDEX_NAME, type = Consts.TYPE_NAME, shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 名字
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 国家
     */
    @Field(type = FieldType.Keyword)
    private String country;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 生日
     */
    @Field(type = FieldType.Date)
    private Date birthday;


//   TODO analyzer参数怎么用 @Field(type = FieldType.Text, analyzer = "ik_smart")

    /**
     * 介绍
     */
    @Field(type = FieldType.Text)
    private String remark;
}
