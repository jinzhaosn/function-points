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

package com.github.jinzhaosn.warning.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 警告记录DTO
 *
 * @auther 961374431@qq.com
 * @date 2022年01月03日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarningRecordDTO {
    private Long id; // 主键
    private String systemName; // 所属系统名称
    private String serviceGroup; // 服务组
    private String serviceName; // 服务名称
    private String serviceUniqueCode; // 服务唯一编码
    private String codeSourcePath; // 代码路径
    private Integer warningLevel; // 警告等级
    private String date; // 发生日期
    private String createTime; // 记录时间
    private String problemDesc; // 错误描述
}
