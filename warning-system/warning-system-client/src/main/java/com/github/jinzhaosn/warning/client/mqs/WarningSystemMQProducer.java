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

package com.github.jinzhaosn.warning.client.mqs;

import com.github.jinzhaosn.warning.enums.WarningLevelEnum;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.jinzhaosn.warning.constant.WarningSystemRabbitMQConstant.EXCHANGE_TOPIC_NAME;
import static com.github.jinzhaosn.warning.enums.WarningLevelEnum.INFO;
import static org.springframework.amqp.core.MessageDeliveryMode.NON_PERSISTENT;
import static org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT;

/**
 * 警告系统MQ消息生成
 *
 * @auther 961374431@qq.com
 * @date 2022年01月15日
 */
public class WarningSystemMQProducer {
    private static final Logger logger = LoggerFactory.getLogger(WarningSystemMQProducer.class);
    private final RabbitTemplate rabbitTemplate;

    /**
     * 批量发送警告记录
     *
     * @param records   记录列表
     * @param levelEnum 警告等级
     */
    public void batchSend(List<WarningRecordDTO> records, WarningLevelEnum levelEnum) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(getDeliveryMode(levelEnum));
        Message message = rabbitTemplate.getMessageConverter().toMessage(records, messageProperties);
        rabbitTemplate.send(EXCHANGE_TOPIC_NAME, levelEnum.getMqRoutingKey(), message);
    }

    /**
     * 对警告等级小于等于INFO的消息，不进行持久化
     *
     * @param levelEnum 警告等级
     * @return 持久化模式
     */
    private MessageDeliveryMode getDeliveryMode(WarningLevelEnum levelEnum) {
        return levelEnum.getLevel() < INFO.getLevel() ? NON_PERSISTENT : PERSISTENT;
    }

    /**
     * 批量发送警告记录
     *
     * @param records 记录列表
     */
    public void batchSend(List<WarningRecordDTO> records) {
        Map<Integer, List<WarningRecordDTO>> levelRecordsMap =
                records.stream().collect(Collectors.groupingBy(WarningRecordDTO::getWarningLevel));
        levelRecordsMap.forEach((level, lst) -> {
            WarningLevelEnum levelEnum = WarningLevelEnum.ofLevel(level);
            batchSend(lst, levelEnum);
        });
    }

    /**
     * 发送警告消息
     *
     * @param record 警告记录
     */
    public void send(WarningRecordDTO record) {
        Integer warningLevel = record.getWarningLevel();
        WarningLevelEnum levelEnum = WarningLevelEnum.ofLevel(warningLevel);
        batchSend(Collections.singletonList(record), levelEnum);
    }

    /**
     * 注入
     *
     * @param rabbitTemplate rabbit客户端
     */
    public WarningSystemMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
