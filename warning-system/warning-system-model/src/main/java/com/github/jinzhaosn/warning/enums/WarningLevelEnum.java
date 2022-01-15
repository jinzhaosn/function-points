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

package com.github.jinzhaosn.warning.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 警告等级
 *
 * @auther 961374431@qq.com
 * @date 2022年01月15日
 */
@Getter
public enum WarningLevelEnum {
    /** TRACE **/
    TRACE(1, "TRACE", "Warning-System.Log.Trace"),
    /** 调试 **/
    DEBUG(2, "DEBUG", "Warning-System.Log.Debug"),
    /** 日常日志等级 **/
    INFO(3, "INFO","Warning-System.Log.Info"),
    /** 错误等级 **/
    ERROR(4, "ERROR","Warning-System.Log.Error"),
    /** 警告 **/
    WARN(5, "WARN","Warning-System.Log.Warn"),
    /** 致命错误 **/
    FATAL(6, "FATAL","Warning-System.Log.Fatal");

    private final Integer level;
    private final String levelName;
    private final String mqRoutingKey;

    WarningLevelEnum(Integer level, String levelName, String mqRoutingKey) {
        this.level = level;
        this.levelName = levelName;
        this.mqRoutingKey = mqRoutingKey;
    }

    /**
     * 通过等级查找警告等级，默认INFO
     *
     * @param level 等级
     * @return 等级枚举
     */
    public static WarningLevelEnum ofLevel(Integer level) {
        return Arrays.stream(values()).filter(value -> value.level.equals(level))
                .findFirst().orElse(INFO);
    }

    /**
     * 通过等级名称获取等级枚举，默认INFO
     *
     * @param levelName 等级名称
     * @return 等级枚举
     */
    public static WarningLevelEnum ofLevelName(String levelName) {
        return Arrays.stream(values()).filter(value -> value.levelName.equalsIgnoreCase(levelName))
                .findFirst().orElse(INFO);
    }
}
