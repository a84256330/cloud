package com.springboot.cloud.demos.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mq") // 修饰类时，消息在根据根据 MessageConverter 转换后的参数类型在@RabbitHandler方法下处理
@Slf4j
public class RabbitReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("Receiver: {}", message);
    }

}
