package com.zh.btp.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class RabbitConsumer {

    /**
     * 指定队列不去消费消息，则会在死信队列中收到消息
     * @param list
     * @param message
     * @param channel
     * @throws IOException
     */
//    @RabbitListener(queues = "normal_queue")
//    public void normal_queue(List<String> list, Message message, Channel channel) throws IOException {
//        log.info("正常队列收到消息时间为:{},收到的消息内容为:{}", LocalDateTime.now(), list.toString());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//
//    @RabbitListener(queues = "dead_queue")
//    public void myDealy(List<String> list, Message message, Channel channel) throws IOException {
//        log.info("死信收到消息时间为:{},收到的消息内容为:{}", LocalDateTime.now(), list.toString());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
}

