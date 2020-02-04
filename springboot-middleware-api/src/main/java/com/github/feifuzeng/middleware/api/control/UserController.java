package com.github.feifuzeng.middleware.api.control;

import com.github.feifuzeng.middleware.api.data.GemerateDateUtils;
import com.github.feifuzeng.middleware.base.entity.User;
import com.github.feifuzeng.middleware.base.result.PlainResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 用户相关控制器
 * @createTime 2019年10月28日 10:11:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * get请求获取参数
     * @return
     */
    @GetMapping("/get/queryUserList")
    public PlainResult<List<User>> queryUserListGet() {
        PlainResult<List<User>> result = new PlainResult<>();
        List<User> list = GemerateDateUtils.initUserList();
        result.setData(list);
        return result;
    }

    /**
     * post请求获取参数
     * @return
     */
    @PostMapping("/post/queryUserList")
    public PlainResult<List<User>> queryUserListPost() {
        PlainResult<List<User>> result = new PlainResult<>();
        List<User> list = GemerateDateUtils.initUserList();
        result.setData(list);
        return result;
    }
}
