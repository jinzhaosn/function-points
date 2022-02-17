/*
 *    Copyright 2021-2022 jinzhaosn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.jinzhaosn.data.pusher.mq.constant;

/**
 * 交流常量类
 *
 * @auther 961374431@qq.com
 * @date 2022年02月17日
 */
public interface ChatConstant {
    /** 广播topic **/
    String CHAT_TOPIC_EXCHANGE_NAME = "chat-topic";

    /** 绑定 **/
    String CHAT_TOPIC_BINDING_ROUTING_KEY = "chat-topic-routing-key";

    /**  **/
    String CHAT_TOPIC_QUEUE_NAME = "chat-topic-queue";
}
