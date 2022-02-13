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

package com.github.jinzhaosn.data.pusher.job;

import com.github.jinzhaosn.common.schedule.AbstractScheduleJob;
import com.github.jinzhaosn.data.pusher.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * 数据推送任务
 *
 * @auther 961374431@qq.com
 * @date 2022年02月12日
 */
@Component
public class DataPusherJob extends AbstractScheduleJob {
    private static final Logger logger = LoggerFactory.getLogger(DataPusherJob.class);
    @Autowired
    SimpMessagingTemplate simp;
    @Autowired
    private SimpUserRegistry userRegistry;

    @Override
    public void doSchedule() {
        ChatMessage message = new ChatMessage();
        message.setType(ChatMessage.MessageType.CHAT);
        message.setSender("system");
        Set<SimpUser> users = userRegistry.getUsers();
        for (SimpUser user : users) {
            String name = user.getName();
            message.setContent(String.format(Locale.ROOT, "system to [%s] current time: [%s]", name, new Date()));
            simp.convertAndSendToUser(name, "/queue/notice", message);
        }
    }

    @Override
    public Trigger getTrigger() {
        return new CronTrigger("*/30 * * * * ?");
    }
}
