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

/**
 * Rest推送数据
 *
 * @auther 961374431@qq.com
 * @date 2022年02月14日
 */
public interface RestPushDataService {
    /**
     * 推送给指定用户
     *
     * @param username 用户名
     * @param dest     目标topic
     * @param data     数据
     */
    void pushToUser(String username, String dest, Object data);

    /**
     * 推送给广播地址
     *
     * @param topic 广播地址/广播主题
     * @param data  数据
     */
    void pushToBroadcast(String topic, Object data);
}
