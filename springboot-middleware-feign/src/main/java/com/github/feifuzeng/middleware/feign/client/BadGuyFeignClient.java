package com.github.feifuzeng.middleware.feign.client;

import com.github.feifuzeng.middleware.feign.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 测试FeignClient
 * @createTime 2019年10月23日 09:37:00
 */
@FeignClient(name = "badGuy", url = "${bab.guy.url}", path = "client")
public interface BadGuyFeignClient {

    /**
     * 随机获取一句甜言蜜语
     *
     * @return
     */
    @GetMapping("SweetNothings")
    String getSweetNothings();

    /**
     * 获取 count 条甜言蜜语
     *
     * @param count 获取甜言蜜语条数
     * @return Json 格式的结果
     */
    @GetMapping("SweetNothings/{count}/Serialization/Json")
    ReturnResult<List<String>> getSweetNothingsJsonByCount(@PathVariable("count") Integer count);

}
