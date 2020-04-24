package com.alanlee.message;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * 基础消费类，定义消费方法
 *
 * @author AlanLee
 */
public interface BaseConsumer {

    void consume(Message message, Channel channel) throws IOException;

}
