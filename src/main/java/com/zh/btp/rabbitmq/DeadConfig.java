package com.zh.btp.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadConfig {

    private String normalQueue="normal_queue";
    private String normalExchange="normal_exchange";
    private String normalRoutingKey="normal_routingkey";

    private String deadQueue="dead_queue";
    private String deadExchange="dead_exchange";
    private String deadRoutingKey="dead_routingkey";


    /**
     * 普通队列绑定死信即可
     * @return
     */
    @Bean
    Queue normalQueue(){
       // return new Queue(normalQueue);
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange",deadExchange);
        map.put("x-dead-letter-routing-key", deadRoutingKey);
        return new Queue(normalQueue,true,false,false,map);
    }

    @Bean
    DirectExchange normalExchange(){
        return new DirectExchange(normalExchange);
    }

    @Bean
    Binding normalBindingExchange(){
        return BindingBuilder.bind( normalQueue()).to(normalExchange()).with(normalRoutingKey);
    }

    /**
     * 死信配置相关
     * @return
     */
    @Bean
    Queue deadQueue(){
        return new Queue(deadQueue);
    }

    @Bean
    DirectExchange deadExchange(){
        return new DirectExchange(deadExchange);
    }

    @Bean
    Binding deadBindingExchange(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(deadRoutingKey);
    }
}

