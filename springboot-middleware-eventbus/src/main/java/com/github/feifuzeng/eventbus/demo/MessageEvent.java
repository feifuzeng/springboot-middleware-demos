package com.github.feifuzeng.eventbus.demo;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 消息实体
 * @createTime 2019年10月16日 17:18:00
 */
public class MessageEvent {

    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }
}