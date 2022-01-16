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

import com.github.jinzhaosn.common.config.ServicePositioner;
import com.github.jinzhaosn.warning.enums.WarningLevelEnum;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 警告记录抽象类
 *
 * @auther 961374431@qq.com
 * @date 2022年01月16日
 */
public abstract class AbstractRecordLogger {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String getLoggerCaller() {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[4];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(traceElement.getFileName()).append(":")
                .append(traceElement.getClassName()).append(":")
                .append(traceElement.getMethodName()).append(":")
                .append(traceElement.getLineNumber());
        return stringBuilder.toString();
    }

    /**
     * 新警告记录，主要预先设置服务相关信息
     *
     * @return 记录
     */
    private WarningRecordDTO newRawWarningRecord() {
        String loggerCaller = getLoggerCaller();
        return WarningRecordDTO.builder()
                .systemName(ServicePositioner.getSystemName())
                .serviceGroup(ServicePositioner.getServiceGroup())
                .serviceName(ServicePositioner.getServiceName())
                .serviceUniqueCode(ServicePositioner.getServiceUniqueCode())
                .date(sdf.format(new Date()))
                .codeSourcePath(loggerCaller)
                .build();
    }

    /**
     * 发送警告记录
     *
     * @param record 警告记录
     */
    abstract void send(WarningRecordDTO record);

    public void trace(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.TRACE.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void trace(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        trace(tuple.getMessage());
    }

    public void trace(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        trace(tuple.getMessage());
    }

    public void trace(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        trace(tuple.getMessage());
    }

    public void trace(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        trace(tuple.getMessage());
    }

    public void debug(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.DEBUG.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void debug(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        debug(tuple.getMessage());
    }

    public void debug(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        debug(tuple.getMessage());
    }

    public void debug(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        debug(tuple.getMessage());
    }

    public void debug(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        debug(tuple.getMessage());
    }

    public void info(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.INFO.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void info(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        info(tuple.getMessage());
    }

    public void info(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        info(tuple.getMessage());
    }

    public void info(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        info(tuple.getMessage());
    }

    public void info(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        info(tuple.getMessage());
    }

    public void warn(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.DEBUG.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void warn(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        warn(tuple.getMessage());
    }

    public void warn(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        warn(tuple.getMessage());
    }

    public void warn(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        warn(tuple.getMessage());
    }

    public void warn(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        warn(tuple.getMessage());
    }

    public void error(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.ERROR.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void error(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        error(tuple.getMessage());
    }

    public void error(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        error(tuple.getMessage());
    }

    public void error(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        error(tuple.getMessage());
    }

    public void error(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        error(tuple.getMessage());
    }

    public void fatal(String log) {
        WarningRecordDTO record = newRawWarningRecord();
        record.setWarningLevel(WarningLevelEnum.FATAL.getLevel());
        record.setProblemDesc(log);

        // 发送
        send(record);
    }

    public void fatal(String pattern, Object arg) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg);
        fatal(tuple.getMessage());
    }

    public void fatal(String pattern, Object arg1, Object arg2) {
        FormattingTuple tuple = MessageFormatter.format(pattern, arg1, arg2);
        fatal(tuple.getMessage());
    }

    public void fatal(String pattern, Object... args) {
        FormattingTuple tuple = MessageFormatter.arrayFormat(pattern, args);
        fatal(tuple.getMessage());
    }

    public void fatal(String pattern, Throwable throwable) {
        FormattingTuple tuple = MessageFormatter.format(pattern, throwable.getMessage());
        fatal(tuple.getMessage());
    }
}
