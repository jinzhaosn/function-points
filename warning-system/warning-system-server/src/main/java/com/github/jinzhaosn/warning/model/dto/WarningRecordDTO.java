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

import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 警告记录DTO
 *
 * @auther 961374431@qq.com
 * @date 2022年01月03日
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WarningRecordDTO extends WarningRecordEntity {

    /**
     * 转换成警告记录实体
     *
     * @return 警告记录实体
     */
    public WarningRecordEntity toWarningRecordEntity() {
        WarningRecordEntity entity = new WarningRecordEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    /**
     * 批量转换成警告记录实体
     *
     * @param recordDTOS DTO列表
     * @return 警告记录实体
     */
    public static List<WarningRecordEntity> batchTransformTo(List<WarningRecordDTO> recordDTOS) {
        if (CollectionUtils.isEmpty(recordDTOS)) {
            return Collections.emptyList();
        }
        return recordDTOS.stream().map(WarningRecordDTO::toWarningRecordEntity)
                .collect(Collectors.toList());
    }
}
