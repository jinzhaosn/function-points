/*
 *    Copyright 2021-2022 jinzhaosn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.jinzhaosn.data.pusher.mq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.jinzhaosn.data.pusher.mq.constant.ChatConstant.CHAT_TOPIC_BINDING_ROUTING_KEY;
import static com.github.jinzhaosn.data.pusher.mq.constant.ChatConstant.CHAT_TOPIC_EXCHANGE_NAME;
import static com.github.jinzhaosn.data.pusher.mq.constant.ChatConstant.CHAT_TOPIC_QUEUE_NAME;

/**
 * Rabbit配置
 *
 * @auther 961374431@qq.com
 * @date 2022年02月14日
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig implements InitializingBean {
    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * Jackson2
     *
     * @return 消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 配置警告系统Exchange
     *
     * @return Exchange
     */
    public Exchange chatExchange() {
        return ExchangeBuilder.topicExchange(CHAT_TOPIC_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 警告系统队列Queue
     *
     * @return 队列对象
     */
    public Queue chatQueue() {
        return QueueBuilder.durable(CHAT_TOPIC_QUEUE_NAME).build();
    }

    /**
     * 警告系统Queue绑定Exchange
     *
     * @return 绑定对象
     */
    public Binding chatBinding(Exchange chatExchange, Queue chatQueue) {
        return BindingBuilder.bind(chatQueue)
                .to(chatExchange).with(CHAT_TOPIC_BINDING_ROUTING_KEY).noargs();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        Exchange exchange = chatExchange();
        Queue queue = chatQueue();
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(chatBinding(exchange, queue));
    }
}
