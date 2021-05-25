package com.javatraining.jpaexample.messaging;

import java.util.Date;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MQUtil {
    @Autowired
    RabbitTemplate template;

    @Value("${queue}")
    private String QUEUE;

    @Value("${exchange}")
    private String EXCHANGE;

    @Value("${router}")
    private String ROUTER;


    public boolean publishMessage(CustomMessage message) {

        message.setMessageId(UUID.randomUUID()
                .toString());
        message.setMessageDate(new Date());
        template.convertAndSend(EXCHANGE, ROUTER, message);

        return true;
    }
}
