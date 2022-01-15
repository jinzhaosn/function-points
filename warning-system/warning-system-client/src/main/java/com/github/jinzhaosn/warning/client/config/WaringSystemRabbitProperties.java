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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 警告系统Rabbit属性
 *
 * @auther 961374431@qq.com
 * @date 2022年01月15日
 */
@Configuration
@PropertySource("classpath:/warning-system-rabbit.properties")
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class WaringSystemRabbitProperties {
    private String host; // 主机
    private Integer port; // 端口
    private String virtualHost; // 虚拟主机
    private String username; // 用户名称
    private String password; // 密码
}
