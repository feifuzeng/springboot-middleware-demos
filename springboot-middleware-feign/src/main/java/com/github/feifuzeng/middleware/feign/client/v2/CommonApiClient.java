package com.github.feifuzeng.middleware.feign.client.v2;

import com.github.feifuzeng.middleware.base.entity.User;
import com.github.feifuzeng.middleware.base.result.PlainResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 利用spring cloud feign 替代httpclient请求外部接口client-方式2
 * @createTime 2019年10月23日 16:54:00
 */
@FeignClient(name = "apiClientTwo", url = "${test.url}", path = "api", configuration = FeignClientConfiguration.class)
public interface CommonApiClient {

    /**
     * 查询用户信息接口-第二种方式
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/queryUser")
    PlainResult<User> queryUser(@RequestParam("userId") String userId);
}
