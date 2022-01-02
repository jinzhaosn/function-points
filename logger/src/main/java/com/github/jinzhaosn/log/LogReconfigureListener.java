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

package com.github.jinzhaosn.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * log配置
 *
 * @auther 961374431@qq.com
 * @date 2022年01月02日
 */
public class LogReconfigureListener implements GenericApplicationListener {
    private static final Class<?>[] EVENT_TYPES = {ApplicationStartedEvent.class};
    private static final String LOG4J_XML_FILE_PATH = "log4j2-rc.xml";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            // 重新配置日志
            ClassLoader classLoader = this.getClass().getClassLoader();
            reconfigureLogManager(classLoader);
        }
    }

    public void reconfigureLogManager(ClassLoader classLoader) {
        // 获取配置文件流
        try (InputStream xmlInputStream = classLoader.getResourceAsStream(LOG4J_XML_FILE_PATH)) {
            BufferedInputStream log4j2XmlBis = new BufferedInputStream(xmlInputStream);

            int size = log4j2XmlBis.available();
            byte[] buffer = new byte[size];
            log4j2XmlBis.read(buffer, 0, size);

            // 重新配置
            reconfigureLogManager(buffer);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void reconfigureLogManager(byte[] configBytes) throws IOException {
        // 创建XML配置解析
        ConfigurationFactory configFactory = XmlConfigurationFactory.getInstance();
        // 设置配置方式为XML
        ConfigurationFactory.setConfigurationFactory(configFactory);

        // 转换配置为配置源
        ConfigurationSource configurationSource = new ConfigurationSource(new ByteArrayInputStream(configBytes));
        // 获取日志环境
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        // 获取新的配置
        Configuration configuration = configFactory.getConfiguration(context, configurationSource);
        // 使用新配置配置日志
        context.reconfigure(configuration);
    }

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return this.isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            int size = supportedTypes.length;
            for (int index = 0; index < size; ++index) {
                Class<?> supportedType = supportedTypes[index];
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }
}