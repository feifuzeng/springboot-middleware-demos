package com.github.feifuzeng.middleware.feign.service.impl;

import com.github.feifuzeng.middleware.feign.service.BadGuyFeignClient;
import com.github.feifuzeng.middleware.feign.service.BadGuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 接口实现类
 * @createTime 2019年10月23日 09:45:00
 */
@Component
public class BadServiceImpl implements BadGuyService {

    @Autowired
    private BadGuyFeignClient badGuyFeignClient;

    @Override
    public List<String> getQuotations(Integer count) {
        if (count == null || count <= 0) {
            String singleQuotation = badGuyFeignClient.getSweetNothings();
            return new ArrayList<String>() {{
                add(singleQuotation);
            }};
        }
        return badGuyFeignClient.getSweetNothingsJsonByCount(count).getReturnObj();
    }
}

