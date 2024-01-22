package com.example.publisher.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by 19920728 on 2024/1/22 17:58
 */
@SpringBootTest
public class SpringAmqpTest {

    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testQueue() {
        // 队列名称
        String queueName = "hello.queue1";
        // 消息
        String message = "hello, spring amqp";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
