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
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.sockjs.transport.SockJsServiceConfig;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

import java.net.URI;

/**
 * SockJsWebSocketHandler
 *
 * @auther 961374431@qq.com
 * @date 2022年01月29日
 */
public class SockJsWebSocketHandler
        extends org.springframework.web.socket.sockjs.transport.handler.SockJsWebSocketHandler{
    private static final Logger logger = LoggerFactory.getLogger(SockJsWebSocketHandler.class);

    public SockJsWebSocketHandler(
            SockJsServiceConfig serviceConfig, WebSocketHandler webSocketHandler,
            WebSocketServerSockJsSession sockJsSession) {
        super(serviceConfig, webSocketHandler, sockJsSession);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession wsSession) throws Exception {
        URI uri = wsSession.getUri();
        logger.info("session established uri: [{}]", uri);
        super.afterConnectionEstablished(wsSession);
    }
}
