package com.github.feifuzeng.middleware.elasticsearch._cat.indies.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年08月29日 16:39:00
 */
@Data
public class CatIndexObject implements Serializable {

    private List<CatIndexInfo> catIndexInfoList;
}
