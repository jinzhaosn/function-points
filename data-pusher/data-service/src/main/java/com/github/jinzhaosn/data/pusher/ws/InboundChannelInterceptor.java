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

import com.github.jinzhaosn.data.pusher.model.StompPrincipal;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.List;

/**
 * Channel拦截器
 *
 * @auther 961374431@qq.com
 * @date 2022年02月13日
 */
public class InboundChannelInterceptor implements ChannelInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InboundChannelInterceptor.class);

    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            List<String> usernames = accessor.getNativeHeader("username");
            if (!CollectionUtils.isEmpty(usernames)) {
                accessor.setUser(new StompPrincipal(usernames.get(0)));
            }
        }
        return message;
    }
}
