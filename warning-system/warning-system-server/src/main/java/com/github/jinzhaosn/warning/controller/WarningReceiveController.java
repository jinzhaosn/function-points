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

package com.github.jinzhaosn.warning.controller;

import com.github.jinzhaosn.common.ResultVo;
import com.github.jinzhaosn.warning.model.dto.WarningRecordDTO;
import com.github.jinzhaosn.warning.service.impl.WarningRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 警告接受
 *
 * @auther 961374431@qq.com
 * @date 2022年01月02日
 */
@RestController
@RequestMapping("/v1")
public class WarningReceiveController {
    private static final Logger logger = LoggerFactory.getLogger(WarningReceiveController.class);
    @Autowired
    WarningRecordService warningRecordService;

    @PostMapping("/warningRecord/save")
    public ResultVo<?> saveWarningRecord(@RequestBody WarningRecordDTO recordDTO) {

        return ResultVo.success();
    }
}
