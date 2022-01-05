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
package com.github.jinzhaosn.model.mapper;


import com.github.jinzhaosn.warning.WarningSystemApplication;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import com.github.jinzhaosn.warning.model.mapper.WarningRecordMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * .
 *
 * @auther 961374431@qq.com
 * @date 2022年01月05日
 */
@SpringBootTest(classes = {WarningSystemApplication.class})
public class WarningRecordMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(WarningRecordMapperTest.class);

    /**
     * 测试Mapper
     */
    @Test
    public void recordMapperTest() {
        WarningRecordDTO recordDTO = new WarningRecordDTO();
        recordDTO.setSystem("testSystem");
        recordDTO.setServiceGroup("testGroup");
        recordDTO.setServiceName("testService");
        recordDTO.setServiceUniqueCode("0981234576abc");

        WarningRecordEntity entity = WarningRecordMapper.INSTANCE.toEntity(recordDTO);
        logger.info("source record dto: [{}]", recordDTO);
        logger.info("after mapper record entity: [{}]", entity);
    }
}
