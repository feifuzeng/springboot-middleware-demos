package com.github.feifuzeng.eventbus.demo;

import com.google.common.eventbus.Subscribe;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 订阅者1
 * @createTime 2019年10月17日 10:09:00
 */
@Component
@Log4j2
public class EventBusSubscribeOne {

    @Subscribe
    public void write(MessageEvent messageEvent) {
       log.info("CURRENT TIME:{},RECEIVE MESSAGE:{}", LocalDateTime.now() ,messageEvent.message);
    }
}
