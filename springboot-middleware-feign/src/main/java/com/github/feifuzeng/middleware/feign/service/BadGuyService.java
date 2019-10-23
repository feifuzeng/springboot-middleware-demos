package com.github.feifuzeng.middleware.feign.service;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 接口定义
 * @createTime 2019年10月23日 09:45:00
 */
public interface BadGuyService {

    /**
     * 获取
     * @param count
     * @return
     */
    public List<String> getQuotations(Integer count);

}
