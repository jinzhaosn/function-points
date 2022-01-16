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

package com.github.jinzhaosn.common.config;

import com.github.jinzhaosn.common.util.IpUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 服务定位器
 *
 * @auther 961374431@qq.com
 * @date 2022年01月16日
 */
@Configuration
@ConfigurationProperties(prefix = "service.position")
public class ServicePositioner implements InitializingBean {
    private static String systemName;
    private static String serviceGroup;
    private static String serviceName;
    private static String serviceUniqueCode;
    private static Integer serverPort;
    private static final String serverIp;

    static {
        serverIp = IpUtil.getServerIp();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(serverIp).append(":")
                .append(serverPort).append(":").append(System.currentTimeMillis());
        ServicePositioner.serviceUniqueCode = stringBuilder.toString();
    }

    public static String getServerIp() {
        return serverIp;
    }

    public void setSystemName(String systemName) {
        ServicePositioner.systemName = systemName;
    }

    public void setServiceGroup(String serviceGroup) {
        ServicePositioner.serviceGroup = serviceGroup;
    }

    public void setServiceName(String serviceName) {
        ServicePositioner.serviceName = serviceName;
    }

    public void setServerPort(Integer serverPort) {
        ServicePositioner.serverPort = serverPort;
    }

    public static String getSystemName() {
        return systemName;
    }

    public static String getServiceGroup() {
        return serviceGroup;
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static String getServiceUniqueCode() {
        return serviceUniqueCode;
    }

    public static Integer getServerPort() {
        return serverPort;
    }
}
