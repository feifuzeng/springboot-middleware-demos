package com.github.feifuzeng.middleware.feign.entity;

import lombok.Data;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 用户基类
 * @createTime 2019年10月23日 16:30:00
 */
@Data
public class User {

    private String userName;

    private Integer age;

    private String hobby;
}
