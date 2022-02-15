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

package com.github.jinzhaosn.data.pusher.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.jinzhaosn.common.model.ResultVo;
import com.github.jinzhaosn.data.pusher.service.RestPushDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据推送，用于外部系统REST调用传递数据，用于websocket推送
 *
 * @auther 961374431@qq.com
 * @date 2022年02月14日
 */
@RestController
@RequestMapping("/v1/data")
public class DataPusherController {
    private static final Logger logger = LoggerFactory.getLogger(DataPusherController.class);
    @Autowired
    RestPushDataService pushService;

    /**
     * 推送数据给用户
     *
     * @param username 用户
     * @param dest     目标地址
     * @param data     数据
     * @return 推送结果
     */
    @PostMapping("/pushToUser")
    public ResultVo<?> pushToUser(
            @RequestParam("username") String username, @RequestParam("dest") String dest,
            @RequestBody JSONObject data) {
        logger.info("push to user:[{}] dest:[{}]", username, dest);
        pushService.pushToUser(username, dest, data);
        return ResultVo.success();
    }

    /**
     * 推送数据给主题
     *
     * @param topic 主题
     * @param data  数据
     * @return 推送结果
     */
    @PostMapping("/pushToBroadcast")
    public ResultVo<?> pushToBroadcast(String topic, @RequestBody JSONObject data) {
        logger.info("push to broadcast: [{}]", topic);
        pushService.pushToBroadcast(topic, data);
        return ResultVo.success();
    }
}
