package com.github.feifuzeng.middleware.feign.control;

import com.github.feifuzeng.middleware.base.entity.User;
import com.github.feifuzeng.middleware.base.result.PlainResult;
import com.github.feifuzeng.middleware.feign.client.v1.BasicApiClient;
import com.github.feifuzeng.middleware.feign.client.v2.CommonApiClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年10月23日 16:17:00
 */
@RestController
@RequestMapping("/api/common")
@Log4j2
public class CommonApiController {

    @Resource
    private BasicApiClient basicApiClient;

    @Resource
    private CommonApiClient commonApiClient;

    @Value("${test.token}")
    private String token;

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/queryUser")
    public PlainResult<User> queryUser(String userId) {
        PlainResult<User> result = basicApiClient.queryUser(userId, token);
        log.info(result.toString());
        return result;
    }

    /**
     * 查询用户信息-方式2
     *
     * @param userId
     * @return
     */
    @RequestMapping("/queryUserTwo")
    public PlainResult<User> queryUserTwo(String userId) {
        PlainResult<User> result = commonApiClient.queryUser(userId);
        log.info(result.toString());
        return result;
    }
}
