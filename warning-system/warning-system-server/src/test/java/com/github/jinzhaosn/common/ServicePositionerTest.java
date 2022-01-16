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

package com.github.jinzhaosn.common;

import com.github.jinzhaosn.common.config.ServicePositioner;
import com.github.jinzhaosn.warning.WarningSystemApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 服务IP工具类测试
 *
 * @auther 961374431@qq.com
 * @date 2022年01月16日
 */
@SpringBootTest(classes = {WarningSystemApplication.class})
public class ServicePositionerTest {
    private static final Logger logger = LoggerFactory.getLogger(ServicePositionerTest.class);
    @Autowired
    ServicePositioner servicePositioner;

    /**
     * 服务IP工具类测试
     */
    @Test
    public void servicePositionerTest() {
        logger.info("system name: [{}]", ServicePositioner.getSystemName());
        logger.info("service group: [{}]", ServicePositioner.getServiceGroup());
        logger.info("service name: [{}]", ServicePositioner.getServiceName());
        logger.info("service ip: [{}]", ServicePositioner.getServerIp());
        logger.info("service port: [{}]", ServicePositioner.getServerPort());
        logger.info("service unique code: [{}]", ServicePositioner.getServiceUniqueCode());
    }
}
