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

package com.github.jinzhaosn.warning.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 警告记录
 *
 * @auther 961374431@qq.com
 * @date 2022年01月02日
 */
@TableName(value = "t_warning_record")
public class WarningRecordEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // 主键
    private String system; // 所属系统名称
    private String serviceGroup; // 服务组
    private String serviceName; // 服务名称
    private String serviceUniqueCode; // 服务唯一编码
    private String codeSourcePath; // 代码路径
    private Integer warningLevel; // 警告等级
    private LocalDate date; // 发生日期
    private LocalDateTime createTime; // 记录时间
    private String problemDesc; // 错误描述
}