package com.github.feifuzeng.eventbus.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 注册机
 * @createTime 2019年10月17日 10:10:00
 */
@Component
public class EventBusRegister {

    @Autowired
    private EventBusSubscribeOne eventBusSubscribeOne;

    @Autowired
    private EventBusSubscribeTwo eventBusSubscribeTwo;


    public  void push() throws Exception{
        register();
        for(int i=1;i<1000;i++){
            MessageEvent messageEvent = new MessageEvent("第"+i+"条消息");
            EventBusClient.post(messageEvent);
            Thread.sleep(1000);
        }
    }


    public void register(){
        EventBusClient.register(eventBusSubscribeOne);
        EventBusClient.register(eventBusSubscribeTwo);
    }
}
