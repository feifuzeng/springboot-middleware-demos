package com.github.feifuzeng.eventbus.demo;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 定义eventBus客户端
 * @createTime 2019年10月16日 17:52:00
 */
public class EventBusClient {

    private final static     String        IDENTIFIER      = "StyleEventBus";
    private final static AsyncEventBus BATCH_EVENT_BUS = new AsyncEventBus(IDENTIFIER, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2 + 1));
//    private final static     EventBus      BATCH_EVENT_BUS = new EventBus(IDENTIFIER);


    /**
     * 提交事件
     */
    public static void post(final MessageEvent message) {
        BATCH_EVENT_BUS.post(message);
    }

    /**
     * 注册监听
     */
    public static void register(Object handler) {
        BATCH_EVENT_BUS.register(handler);
    }

    /**
     * 取消注册监听
     */
    public static void unregister(Object handler) {
        BATCH_EVENT_BUS.unregister(handler);
    }
}
