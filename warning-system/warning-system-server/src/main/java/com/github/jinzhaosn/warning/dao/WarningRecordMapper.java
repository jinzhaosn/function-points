/**
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

package com.github.jinzhaosn.warning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jinzhaosn.warning.model.entity.WarningRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * 警告记录DAO
 *
 * @auther 961374431@qq.com
 * @date 2022年01月02日
 */
@Repository
public interface WarningRecordMapper extends BaseMapper<WarningRecordEntity> {

}
