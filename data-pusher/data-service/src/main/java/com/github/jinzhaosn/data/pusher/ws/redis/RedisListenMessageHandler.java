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

import org.springframework.data.redis.listener.Topic;

import java.util.Collection;

/**
 * Redis消息监听
 *
 * @auther 961374431@qq.com
 * @date 2022年02月13日
 */
public interface RedisListenMessageHandler<T> {
    /**
     * 获取消息类型
     *
     * @return 消息类
     */
    Class<T> getMessageType();

    /**
     * 获取监听的topics
     *
     * @return topics
     */
    Collection<? extends Topic> getTopics();

    /**
     * 处理消息
     *
     * @param message 消息
     */
    void handleMessage(T message);
}
