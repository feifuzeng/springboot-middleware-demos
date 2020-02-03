package com.github.feifuzeng.middleware.mybatis.service.impl;

import com.github.feifuzeng.middleware.mybatis.domain.mapper.UserPOMapper;
import com.github.feifuzeng.middleware.mybatis.domain.po.UserPO;
import com.github.feifuzeng.middleware.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description service impl
 * @createTime 2020年01月22日 11:18:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserPOMapper userPOMapper;

    @Override
    public List<UserPO> queryList() {
        userPOMapper.queryList();
        userPOMapper.queryList();
        return userPOMapper.queryList();
    }

    @Override
    public int insert(UserPO userPO) {
        return userPOMapper.insert(userPO);
    }
}
