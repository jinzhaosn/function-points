/**
 * Copyright 2021-2022 jinzhaosn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jinzhaosn.warning.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.jinzhaosn.warning.constant.WarningSystemRabbitMQConstant.BINDING_ROUTING_KEY;
import static com.github.jinzhaosn.warning.constant.WarningSystemRabbitMQConstant.EXCHANGE_TOPIC_NAME;
import static com.github.jinzhaosn.warning.constant.WarningSystemRabbitMQConstant.QUEUE_NAME;

/**
 * RabbitMQ配置
 *
 * @auther 961374431@qq.com
 * @date 2022年01月14日
 */
@Configuration
@EnableRabbit
public class RabbitMqConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

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
    @Bean
    public Exchange warningSystemExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_NAME).durable(true).build();
    }

    /**
     * 警告系统队列Queue
     *
     * @return 队列对象
     */
    @Bean
    public Queue warningSystemQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    /**
     * 警告系统Queue绑定Exchange
     *
     * @return 绑定对象
     */
    @Bean
    public Binding warningSystemBinding(Exchange warningSystemExchange, Queue warningSystemQueue) {
        return BindingBuilder.bind(warningSystemQueue)
                .to(warningSystemExchange).with(BINDING_ROUTING_KEY).noargs();
    }
}
