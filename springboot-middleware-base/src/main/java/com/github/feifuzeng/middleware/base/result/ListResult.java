package com.github.feifuzeng.middleware.base.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 类ListResult.java的实现描述：返回批量查询的结果集的泛型实现
 */
public class ListResult<T> extends BaseResult {
    private static final long serialVersionUID = 5741020370203813418L;

    private List<T>           data             = new ArrayList<T>();

    /**
     * 根据查询
     */
    private int               count;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        if (data != null) {
            this.count = data.size();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
