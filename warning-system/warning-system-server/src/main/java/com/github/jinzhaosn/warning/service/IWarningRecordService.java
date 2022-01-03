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

package com.github.jinzhaosn.warning.service;

import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;

import java.util.List;

/**
 * 警告记录服务接口
 *
 * @auther 961374431@qq.com
 * @date 2022年01月03日
 */
public interface IWarningRecordService {

    /**
     * 保存警告记录
     *
     * @param warningRecordDTOS 警告记录DTO列表
     * @return 更新条目
     */
    int saveWarningRecords(List<WarningRecordDTO> warningRecordDTOS);
}
