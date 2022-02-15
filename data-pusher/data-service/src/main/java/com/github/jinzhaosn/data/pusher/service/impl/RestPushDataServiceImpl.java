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

package com.github.jinzhaosn.data.pusher.service.impl;

import com.github.jinzhaosn.data.pusher.service.RestPushDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Rest数据推送服务
 *
 * @auther 961374431@qq.com
 * @date 2022年02月14日
 */
@Service
public class RestPushDataServiceImpl implements RestPushDataService {
    private static final Logger logger = LoggerFactory.getLogger(RestPushDataServiceImpl.class);
    @Autowired
    ChatMessageServiceImpl chatService;

    @Override
    public void pushToUser(String username, String dest, Object data) {
        logger.info("push to user: [{}] dest:[{}]", username, dest);
        chatService.sendToUser(username, dest, data);
    }

    @Override
    public void pushToBroadcast(String topic, Object data) {
        logger.info("push to broadcast: [{}]", topic);
        chatService.broadcastTo(topic, data);
    }
}
