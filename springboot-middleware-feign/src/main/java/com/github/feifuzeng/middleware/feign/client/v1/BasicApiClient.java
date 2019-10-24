package com.github.feifuzeng.middleware.feign.client.v1;

import com.github.feifuzeng.middleware.base.result.PlainResult;
import com.github.feifuzeng.middleware.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 利用spring cloud feign 替代httpclient请求外部接口client
 * @createTime 2019年10月23日 15:23:00
 */
@FeignClient(name = "apiClient", url = "${test.url}", path = "api")
public interface BasicApiClient {


    /**
     * 查询用户信息接口-第一种方式
     *
     * @param userId
     * @param token
     * @return
     */
    @GetMapping(value = "/queryUser")
    PlainResult<User> queryUser(@RequestParam("userId") String userId,
                                @RequestHeader(name = "token") String token);

}
