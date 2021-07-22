package com.czerwo.ftrot.services;

import com.czerwo.ftrot.config.JmsConfig;
import com.czerwo.ftrot.domain.TestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final JmsTemplate jmsTemplate;

    public void createMessage() {

        TestMessage testMessage = TestMessage.builder()
                .messageName("Test123")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.TEST_MESSAGE, testMessage);
    }
}
