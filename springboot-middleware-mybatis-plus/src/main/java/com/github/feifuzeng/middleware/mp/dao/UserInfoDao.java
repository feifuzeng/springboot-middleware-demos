package com.github.feifuzeng.middleware.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.feifuzeng.middleware.mp.entity.UserInfoEntity;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年08月27日 19:34:00
 */
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

    /**
     * 查询大于该分数的学生
     * @Author Sans
     * @CreateTime 2019/6/9 14:28
     * @Param  page  分页参数
     * @Param  fraction  分数
     * @Return IPage<UserInfoEntity> 分页数据
     */
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
}