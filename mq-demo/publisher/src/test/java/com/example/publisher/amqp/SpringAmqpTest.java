package com.example.publisher.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String message = "hello, spring amqp at " + dateTimeStr;
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * 向队列中不停的发送消息，模拟消息堆积
     *
     * @throws InterruptedException
     */
    @Test
    public void testHelloQueue2() throws InterruptedException {
        // 队列名称
        String queueName = "hello.queue2";
        // 消息
        String message = "hello, message_";
        // 循环发送
        for (int i = 0; i < 50; i++) {
            // 发送消息，每20毫秒发送一次，相当于每秒发送50条消息
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }
}
