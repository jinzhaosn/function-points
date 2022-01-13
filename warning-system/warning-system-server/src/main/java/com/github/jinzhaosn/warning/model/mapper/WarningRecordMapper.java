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
package com.github.jinzhaosn.warning.model.mapper;

import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 警告记录映射
 *
 * @auther 961374431@qq.com
 * @date 2022年01月04日
 */
@Mapper
public interface WarningRecordMapper {
    // Mapper实例
    WarningRecordMapper INSTANCE = Mappers.getMapper(WarningRecordMapper.class);

    /**
     * DTO转化成实体
     *
     * @param recordDTO DTO
     * @return 实体
     */
    @Mapping(source = "date", target = "occurDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    WarningRecordEntity toEntity(WarningRecordDTO recordDTO);

    /**
     * DTO批量转换实体
     *
     * @param recordDTOList DTO列表
     * @return 实体列表
     */
    List<WarningRecordEntity> toEntities(List<WarningRecordDTO> recordDTOList);
}
