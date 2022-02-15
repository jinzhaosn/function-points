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

package com.github.jinzhaosn.data.pusher.service;

import java.util.List;

/**
 * 交流消息服务
 *
 * @auther 961374431@qq.com
 * @date 2022年02月14日
 */
public interface ChatMessageService {

    /**
     * 批量广播
     *
     * @param topic    广播地址
     * @param messages 消息
     */
    void broadcastTo(String topic, List<Object> messages);

    /**
     * 广播一条消息
     *
     * @param topic   广播地址
     * @param message 消息
     */
    void broadcastTo(String topic, Object message);

    /**
     * 发送给个人
     *
     * @param username 用户名称
     * @param dest     目标地址
     * @param message  消息
     */
    void sendToUser(String username, String dest, Object message);
}
