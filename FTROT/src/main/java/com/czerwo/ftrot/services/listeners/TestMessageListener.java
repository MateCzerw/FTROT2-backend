package com.czerwo.ftrot.services.listeners;

import com.czerwo.ftrot.config.JmsConfig;
import com.czerwo.ftrot.domain.TestMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class TestMessageListener {
    @JmsListener(destination = JmsConfig.TEST_MESSAGE)
    public void listen(TestMessage testMessage) {
        System.out.println(testMessage);
    }
}
