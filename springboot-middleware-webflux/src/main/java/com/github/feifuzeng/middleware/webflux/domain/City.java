package com.github.feifuzeng.middleware.webflux.domain;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 城市实体类
 * @createTime 2019年10月15日 17:15:00
 */

import lombok.Data;

@Data
public class City {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

}