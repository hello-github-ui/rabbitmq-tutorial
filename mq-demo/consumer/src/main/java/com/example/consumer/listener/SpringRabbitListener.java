package com.example.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by 19920728 on 2024/1/23 22:37
 */
@Component
public class SpringRabbitListener {

    /**
     * 利用 RabbitListener 来声明要监听的队列消息
     * 将来一旦监听的队列中有了消息，就会推送给当前服务，调用当前方法，处理消息
     *
     * @param msg 接收到的消息
     */
    @RabbitListener(queues = "hello.queue1")
    public void listenQueueMessage(String msg) throws InterruptedException {
        // 通过 msg 接收消息
        System.err.println("spring 消费者接收到消息: 【" + msg + "】");
    }


    /**
     * 模拟多个消费者绑定同一个 hello.queue2 队列，二者消费能力不同
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(queues = "hello.queue2")
    public void listenHelloQueue1(String msg) throws InterruptedException {
        System.err.println("消费者1接收到消息: 【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }


    /**
     * 模拟多个消费者绑定同一个 hello.queue2 队列，二者消费能力不同
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(queues = "hello.queue2")
    public void listenHelloQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2接收到消息: 【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }
}
