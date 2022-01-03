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

package com.github.jinzhaosn.warning.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jinzhaosn.stream.Flow;
import com.github.jinzhaosn.warning.dao.WarningRecordMapper;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import com.github.jinzhaosn.warning.service.IWarningRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * .
 *
 * @auther 961374431@qq.com
 * @date 2022年01月03日
 */
@Service
public class WarningRecordService extends ServiceImpl<WarningRecordMapper, WarningRecordEntity>
        implements IWarningRecordService {
    private static final Logger logger = LoggerFactory.getLogger(WarningRecordService.class);

    /**
     * 保存警告记录
     *
     * @param warningRecordDTOS 警告记录DTO列表
     * @return 更新条目
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWarningRecords(List<WarningRecordDTO> warningRecordDTOS) {
        logger.info("save warning records: [{}]", warningRecordDTOS);
        Optional<Boolean> result = Flow.choose().when(CollectionUtils.isEmpty(warningRecordDTOS))
                .then(() -> false)
                .otherwise(() -> saveBatch(WarningRecordDTO.batchTransformTo(warningRecordDTOS)))
                .getResult(Boolean.class);
        return result.orElse(false);
    }
}
