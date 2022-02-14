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

import com.github.jinzhaosn.common.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Collection;
import java.util.List;

/**
 * Redis监听注册器
 *
 * @auther 961374431@qq.com
 * @date 2022年02月13日
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.listeners", havingValue = "true")
public class RedisMessageListenerRegister implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(RedisMessageListenerRegister.class);
    private static final String LISTENER_METHOD = "handleMessage";
    @Autowired
    RedisMessageListenerContainer container;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Redis message listener register on application event start");
        if (event.getApplicationContext().getParent() == null) {
            registerListenMessageHandlers();
        }
    }

    /**
     * 注册消息处理
     */
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    private void registerListenMessageHandlers() {
        List<RedisListenMessageHandler> handlers = SpringUtil.getBeansOfType(RedisListenMessageHandler.class);

        // 添加到监听Container
        for (RedisListenMessageHandler handler : handlers) {
            //消息监听处理以及对应的默认处理方法
            MessageListenerAdapter adapter = new MessageListenerAdapter(handler, LISTENER_METHOD);
            //消息的反序列化方式
            adapter.setSerializer(new Jackson2JsonRedisSerializer<>(handler.getMessageType()));
            adapter.afterPropertiesSet();

            container.addMessageListener(adapter, handler.getTopics());
        }
    }

    /**
     * 增加监听
     *
     * @param listener 监听器
     * @param topics   监听主题集合
     */
    public void addMessageListener(MessageListener listener, Collection<? extends Topic> topics) {
        container.addMessageListener(listener, topics);
    }

    /**
     * 增加监听
     *
     * @param listener 监听器
     * @param topic    监听主题
     */
    public void addMessageListener(MessageListener listener, Topic topic) {
        container.addMessageListener(listener, topic);
    }
}
