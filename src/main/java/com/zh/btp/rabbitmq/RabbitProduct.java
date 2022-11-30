package com.zh.btp.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("mq")
public class RabbitProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sx")
    public void mysendDelayMessage() {
        List<String> list = Arrays.asList("123", "456", "789", "899");
        log.info("发送时间:{},发送内容:{}", LocalDateTime.now(), list.toString());
        this.rabbitTemplate.convertAndSend(
                "normal_exchange",
                "normal_routingkey",
                list,
                message -> {
                    message.getMessageProperties().setExpiration("3000");
                    return message;
                }
        );
    }
}

