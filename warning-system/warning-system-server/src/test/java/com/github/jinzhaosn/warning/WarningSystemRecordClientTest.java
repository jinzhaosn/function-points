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

package com.github.jinzhaosn.warning;

import com.github.jinzhaosn.warning.client.feign.WarningSystemRecordClient;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * 警告记录客户端测试
 *
 * @auther 961374431@qq.com
 * @date 2022年01月11日
 */
@SpringBootTest
public class WarningSystemRecordClientTest {
    private static final Logger logger = LoggerFactory.getLogger(WarningSystemRecordClientTest.class);
    @Autowired
    WarningSystemRecordClient recordClient;

    /**
     * 警告系统调用测试
     */
    @Test
    public void warningRecordTest() {
        logger.info("warning record test");

        WarningRecordDTO recordDTO = new WarningRecordDTO();
        recordDTO.setSystemName("zxcv");
        recordDTO.setServiceUniqueCode("2432");
        recordDTO.setCreateTime("2022-01-01 12:01:23");
        recordClient.saveWarningRecords(Collections.singletonList(recordDTO));
    }
}
