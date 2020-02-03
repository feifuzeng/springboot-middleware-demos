package com.github.feifuzeng.middleware.mybatis.domain.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPO implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String dept;

}