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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.security.Principal;

/**
 * Websocket 事件监听器
 *
 * @auther 961374431@qq.com
 * @date 2022年01月28日
 */
@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * 处理会话建立
     *
     * @param event 会话建立事件
     */
    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        logger.info("session connected: [{}]", event);
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = accessor.getUser();
        MessageHeaders headers = accessor.getMessageHeaders();
        logger.info("headers: [{}]", headers);
        String sessionId = accessor.getSessionId();
        SessionHolder.addSession(sessionId);
    }

    /**
     * 处理会话断开事件
     *
     * @param event 会话断开事件
     */
    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        logger.info("session disconnect: [{}]", event);
    }

    @EventListener
    public void handleSubscribe(SessionSubscribeEvent event) {
        logger.info("session subscribe: [{}]", event);
    }

    @EventListener
    public void handleUnsubscribe(SessionUnsubscribeEvent event) {
        logger.info("session unsubscribe: [{}]", event);
    }
}
