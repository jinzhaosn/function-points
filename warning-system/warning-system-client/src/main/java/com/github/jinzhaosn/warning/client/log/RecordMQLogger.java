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

package com.github.jinzhaosn.warning.client.log;

import com.github.jinzhaosn.warning.client.mqs.WarningSystemMQProducer;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;

/**
 * 警告系统MQ Logger
 *
 * @auther 961374431@qq.com
 * @date 2022年01月16日
 */
public class RecordMQLogger extends AbstractRecordLogger{
    private final WarningSystemMQProducer mqProducer;

    public RecordMQLogger(WarningSystemMQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    /**
     * 发送警告记录
     *
     * @param record 警告记录
     */
    @Override
    void send(WarningRecordDTO record) {
        mqProducer.send(record);
    }
}
