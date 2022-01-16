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

package com.github.jinzhaosn.warning.client.config;

import com.github.jinzhaosn.warning.client.feign.WarningSystemRecordClient;
import com.github.jinzhaosn.warning.client.log.RecordFeignLogger;
import com.github.jinzhaosn.warning.client.log.RecordMQLogger;
import com.github.jinzhaosn.warning.client.mqs.WarningSystemMQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 警告记录配置
 *
 * @auther 961374431@qq.com
 * @date 2022年01月16日
 */
@Configuration
public class RecordLoggerConfig {

    /**
     * 构建MQ记录客户端
     *
     * @param rabbitTemplate Rabbit客户端
     * @return MQ记录客户端
     */
    @Bean
    @Primary
    @ConditionalOnClass(RabbitAutoConfiguration.class)
    public RecordMQLogger recordMQLogger(RabbitTemplate rabbitTemplate) {
        WarningSystemMQProducer mqProducer = new WarningSystemMQProducer(rabbitTemplate);
        return new RecordMQLogger(mqProducer);
    }

    /**
     * Feign日志记录器
     *
     * @param recordClient Feign记录客户端
     * @return 日志记录器
     */
    @Bean
    @ConditionalOnMissingBean(RecordMQLogger.class)
    public RecordFeignLogger recordFeignLogger(WarningSystemRecordClient recordClient) {
        return new RecordFeignLogger(recordClient);
    }
}
