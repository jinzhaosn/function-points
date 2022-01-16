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

package com.github.jinzhaosn.warning;

import com.github.jinzhaosn.warning.client.mqs.WarningSystemMQProducer;
import com.github.jinzhaosn.warning.enums.WarningLevelEnum;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * 警告系统MQ测试
 *
 * @auther 961374431@qq.com
 * @date 2022年01月15日
 */
@SpringBootTest(classes = {WarningSystemApplication.class})
public class WarningSystemRecordMQProducerTest {

    /**
     * 测试MQ生成
     */
    @Test
    public void mqProducerTest() {
        IntStream.range(1, 2).forEach(index -> {
            WarningRecordDTO recordDTO = new WarningRecordDTO();
            recordDTO.setSystemName("rabbitmq");
            recordDTO.setServiceUniqueCode("rabbit12345");
            recordDTO.setWarningLevel(WarningLevelEnum.INFO.getLevel());
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
            recordDTO.setCreateTime(sdf.format(new Date()));
            mqProducer().send(recordDTO);
            System.out.println("done");
        });
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 构建MQ警告记录生成器
     *
     * @return MQProducer
     */
    public WarningSystemMQProducer mqProducer() {
        return new WarningSystemMQProducer(rabbitTemplate);
    }
}
