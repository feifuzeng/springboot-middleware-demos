package com.github.feifuzeng.middleware.mybatis.domain.mapper;

import com.github.feifuzeng.middleware.mybatis.domain.po.UserPO;

import java.util.List;

public interface UserPOMapper {

    List<UserPO> queryList();

    int insert(UserPO record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);
}