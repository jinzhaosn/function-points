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

package com.github.jinzhaosn.data.pusher.ws.redis;

import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import com.github.jinzhaosn.data.pusher.ws.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import static com.github.jinzhaosn.data.pusher.constant.ChatConstant.REDIS_CHAT_TOPIC_COMMON;
import static com.github.jinzhaosn.data.pusher.constant.ChatConstant.WS_CHAT_TOPIC_COMMON;

/**
 * topic广播消息处理
 *
 * @auther 961374431@qq.com
 * @date 2022年02月13日
 */
@Component
public class BroadcastTopicMessageHandler implements RedisListenMessageHandler<ChatMessage> {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastTopicMessageHandler.class);
    private final Class<ChatMessage> clazz = ChatMessage.class;
    @Autowired
    ChatMessageService chatService;

    @Override
    public Class<ChatMessage> getMessageType() {
        return clazz;
    }

    @Override
    public Collection<? extends Topic> getTopics() {
        ChannelTopic topic = new ChannelTopic(REDIS_CHAT_TOPIC_COMMON);
        return Collections.singletonList(topic);
    }

    /**
     * 处理消息
     *
     * @param message 消息
     */
    @Override
    public void handleMessage(ChatMessage message) {
        logger.info("handle message: [{}]", message);
        chatService.broadcastTo(WS_CHAT_TOPIC_COMMON, message);
    }
}
