package com.springboot.cloud.demos.producer.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * redis 发布订阅
 */
@Component
public class RedisSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis监听容器创建
     * @param connectionFactory redis消息工厂
     * @param listenerAdapter 消息处理适配器
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 关联频道和消息工厂
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("chat"));
        logger.info("init container:{}", listenerAdapter);
        return container;
    }

    public void send(String channel, String message) {
        logger.info("{}=>{}", channel, message);
        stringRedisTemplate.convertAndSend(channel, message);
    }
}