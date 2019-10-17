package com.github.feifuzeng.eventbus;

import com.github.feifuzeng.eventbus.demo.EventBusRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventbusApplicationTests {

    @Autowired
    private EventBusRegister eventBusRegister;

    @Test
    public void contextLoads() throws Exception{
        eventBusRegister.push();
    }

}
