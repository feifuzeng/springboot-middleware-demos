package com.github.feifuzeng.middleware.feign;

import com.github.feifuzeng.middleware.base.result.BaseResult;

public class ReturnResult<T> extends BaseResult {

    private static final long serialVersionUID = -3767132392732612883L;

    /**
     * 调用返回的数据
     */
    private T                 returnObj;

    /**
     * @return the data
     */
    public T getReturnObj() {
        return returnObj;
    }

    public ReturnResult() {
        super();
    }

    public ReturnResult(T data) {
        this.returnObj = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.returnObj = data;
    }

    @Override
    public String toString() {
        return "{" +
                ",code=" + this.getCode() +
                ",message=" + this.getMessage() +
                ",returnObj=" + returnObj +
                '}';
    }
}
