package com.github.feifuzeng.middleware.mybatis.controller;

import com.github.feifuzeng.middleware.mybatis.domain.po.UserPO;
import com.github.feifuzeng.middleware.mybatis.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description controller
 * @createTime 2020年01月22日 11:21:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询
     * @return
     */
    @RequestMapping("/queryList")
    public List<UserPO> queryList(){
        return userService.queryList();
    }

    /**
     * 插入
     * @param userPO
     * @return
     */
    @RequestMapping("/insert")
    public Integer insert(@RequestBody UserPO userPO){
        return userService.insert(userPO);
    }
}
