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

package com.github.jinzhaosn.data.pusher.ws;

import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交流消息服务
 *
 * @auther 961374431@qq.com
 * @date 2022年02月13日
 */
@Service
public class ChatMessageService {
    private static final Logger logger = LoggerFactory.getLogger(ChatMessageService.class);
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    /**
     * 批量广播
     *
     * @param topic    广播地址
     * @param messages 消息
     */
    public void broadcastTo(String topic, List<ChatMessage> messages) {
        for (ChatMessage message : messages) {
            broadcastTo(topic, message);
        }
    }

    /**
     * 广播一条消息
     *
     * @param topic   广播地址
     * @param message 消息
     */
    public void broadcastTo(String topic, ChatMessage message) {
        try {
            messagingTemplate.convertAndSend(topic, message);
        } catch (Exception exp) {
            logger.error("broadcast to [{}] failed: [{}]", topic, exp.getMessage());
        }
    }

    /**
     * 发送给个人
     *
     * @param username 用户名称
     * @param dest     目标地址
     * @param message  消息
     */
    public void sendToUser(String username, String dest, ChatMessage message) {
        try {
            messagingTemplate.convertAndSendToUser(username, dest, message);
        } catch (Exception exp) {
            logger.error("send to user:[{}] dest:[{}] failed:[{}]", username, dest, message);
        }
    }
}