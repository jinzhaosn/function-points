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

package com.github.jinzhaosn.data.pusher.controller;

import com.alibaba.fastjson.JSON;
import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import static com.github.jinzhaosn.data.pusher.constant.ChatConstant.REDIS_CHAT_TOPIC_COMMON;

/**
 * Websocket 数据Controller
 *
 * @auther 961374431@qq.com
 * @date 2022年01月25日
 */
@Controller
public class WebSocketDataController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketDataController.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 接受消息
     *
     * @param chatMessage 消息
     * @return 发送给个人
     */
    @MessageMapping("/chat.sendMessage")
    @SendToUser("/queue/notice")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("send message: [{}]", chatMessage);
        try {
            redisTemplate.convertAndSend(REDIS_CHAT_TOPIC_COMMON, JSON.toJSONString(chatMessage));
        } catch (Exception e) {
            logger.error("send message exception: [{}]", e.getMessage());
        }
        return chatMessage;
    }

    /**
     * 添加用户
     *
     * @param chatMessage    交流消息
     * @param headerAccessor websocket 消息头
     */
    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("add User: [{}]", chatMessage);
        try {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        } catch (Exception e) {
            logger.error("add User exception: [{}]", e.getMessage());
        }
    }
}
