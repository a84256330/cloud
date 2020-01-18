package com.springboot.cloud.demos.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;


/**
 * 消息处理器
 */
@Component
public class RedisReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handleMessage(String message) {
        logger.info("Received <{}>", message);
    }

    /**
     * 消息处理适配器
     */
    @Bean
    MessageListenerAdapter redisListenerAdapter() {
        logger.info("new listener");
        // 传入一个消息接受的处理器，利用反射的方法调用“handleMessage”
        return new MessageListenerAdapter(this);
    }
}