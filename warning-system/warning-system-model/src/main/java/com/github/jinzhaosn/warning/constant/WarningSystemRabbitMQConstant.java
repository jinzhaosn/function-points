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

package com.github.jinzhaosn.warning.constant;

/**
 * 警告系统MQ常量
 *
 * @auther 961374431@qq.com
 * @date 2022年01月14日
 */
public interface WarningSystemRabbitMQConstant {

    /** Queue名称 **/
    String QUEUE_NAME = "Warning-System-Log-Queue";

    /** Exchange名称 **/
    String EXCHANGE_TOPIC_NAME = "Warning-System-Topic";

    /** Binding **/
    String BINDING_ROUTING_KEY = "Warning-System.Log.#";
}
