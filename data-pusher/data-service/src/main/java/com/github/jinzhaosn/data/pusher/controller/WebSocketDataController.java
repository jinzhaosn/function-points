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

import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;


/**
 * Websocket 数据Controller
 *
 * @auther 961374431@qq.com
 * @date 2022年01月25日
 */
@Controller
public class WebSocketDataController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketDataController.class);

    @MessageMapping("/chat.sendMessage")
    @SendToUser("/queue/notice")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("send message: [{}]", chatMessage);
        try {
        } catch (Exception e) {
            logger.error("send message exception: [{}]", e.getMessage());
        }
        return chatMessage;
    }

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
