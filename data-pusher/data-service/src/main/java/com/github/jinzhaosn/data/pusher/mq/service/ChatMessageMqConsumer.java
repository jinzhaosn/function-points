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

package com.github.jinzhaosn.data.pusher.mq.service;

import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import com.github.jinzhaosn.data.pusher.service.ChatMessageService;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.github.jinzhaosn.data.pusher.mq.constant.ChatConstant.CHAT_TOPIC_QUEUE_NAME;
import static com.github.jinzhaosn.util.ObjectUtil.doSilent;
import static org.springframework.amqp.support.AmqpHeaders.DELIVERY_TAG;

/**
 * 交流消息MQ消费者
 *
 * @auther 961374431@qq.com
 * @date 2022年02月18日
 */
@Service
public class ChatMessageMqConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageMqConsumer.class);
    @Autowired
    ChatMessageService chatService;

    /**
     * 监听交流信息
     *
     * @param message     交流信息
     * @param deliveryTag tag
     * @param channel     通道
     */
    @RabbitListener(queues = {CHAT_TOPIC_QUEUE_NAME})
    public void listenChatMessage(
            @Payload ChatMessage message, @Header("toUser")String toUser, @Header("toSubscribe") String toSubscribe,
            @Header(DELIVERY_TAG) Long deliveryTag, Channel channel) {
        logger.info("receive message: [{}] deliveryTag: [{}], toUser:[{}] toSubscribe:[{}]",
                message, deliveryTag, toUser, toSubscribe);
        boolean ack = false;
        try {
            // 处理消息
            handleMessage(message, toUser, toSubscribe);
            ack = true;
        } catch (Exception exp) {
            logger.error("handle chat message failed: [{}]", exp.getMessage());
        } finally {
            if (ack) {
                doSilent(() -> channel.basicAck(deliveryTag, false));
            }
        }
    }

    /**
     * 处理交流信息
     *
     * @param message 信息
     */
    private void handleMessage(ChatMessage message, String toUser, String toSubscribe) {
        if (StringUtils.isBlank(toUser)) {
            chatService.broadcastTo(toSubscribe, message);
            return;
        }
        chatService.sendToUser(toUser, toSubscribe, message);
    }
}
