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

package com.github.jinzhaosn.warning.mq;

import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import com.github.jinzhaosn.warning.model.mapper.WarningRecordMapper;
import com.github.jinzhaosn.warning.service.IWarningRecordService;
import com.rabbitmq.client.Channel;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.github.jinzhaosn.util.ObjectUtil.doSilent;
import static com.github.jinzhaosn.warning.constant.WarningSystemRabbitMQConstant.QUEUE_NAME;

/**
 * 警告系统MQ消息接收器
 *
 * @auther 961374431@qq.com
 * @date 2022年01月14日
 */
@Component
public class WarningRecordRabbitMessageReceiver {
    private static final Logger logger = LoggerFactory.getLogger(WarningRecordRabbitMessageReceiver.class);
    @Autowired
    IWarningRecordService recordService;

    /**
     * MQ警告消息接收器
     *
     * @param headers    消息头
     * @param recordDTOS 消息
     * @param channel    通道
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void warningRecordListener(
            @Headers Map<String, Object> headers, @Payload List<WarningRecordDTO> recordDTOS, Channel channel) {
        logger.info("warning record listener received message: [{}]", recordDTOS);

        try {
            List<WarningRecordEntity> entities = WarningRecordMapper.INSTANCE.toEntities(recordDTOS);

            logger.info("save batch record entities");
            boolean saveBatch = recordService.saveBatch(entities);
            logger.info("save batch result: [{}]", saveBatch);
            channel.basicAck(MapUtils.getLong(headers, AmqpHeaders.DELIVERY_TAG), false);
        } catch (Exception exp) {
            logger.error("warning record listener deal exception: [{}]", exp.getMessage());
            doSilent(() -> channel.basicNack(MapUtils.getLong(headers, AmqpHeaders.DELIVERY_TAG), false, false));
        }
    }
}
