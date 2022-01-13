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

package com.github.jinzhaosn.warning.client.feign;

import com.github.jinzhaosn.common.model.ResultVo;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 警告记录系统客户端
 *
 * @auther 961374431@qq.com
 * @date 2022年01月11日
 */
@FeignClient(name = "WarningSystemRecordClient")
public interface WarningSystemRecordClient {

    /**
     * 保存警告记录成功
     *
     * @param recordList 警告记录
     * @return 结果Vo
     */
    @PostMapping(value = "/v1/warningRecord/save", produces = "application/json")
    ResultVo<?> saveWarningRecords(@RequestBody List<WarningRecordDTO> recordList);
}
