package com.github.feifuzeng.middleware.mybatis.service;

import com.github.feifuzeng.middleware.mybatis.domain.po.UserPO;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description service interface
 * @createTime 2020年01月22日 10:57:00
 */
public interface UserService {

    List<UserPO> queryList();

    int insert(UserPO userPO);
}
