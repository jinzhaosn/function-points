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

package com.github.jinzhaosn.data.pusher.ws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Session Holder
 *
 * @auther 961374431@qq.com
 * @date 2022年02月10日
 */
public class SessionHolder {
    private static final Set<String> sessionCache = new CopyOnWriteArraySet<>();

    public static void addSession(String sessionId) {
        sessionCache.add(sessionId);
    }

    public static boolean remove(String sessionId) {
        return sessionCache.remove(sessionId);
    }

    public static List<String> getSessions() {
        return Collections.unmodifiableList(new ArrayList<>(sessionCache));
    }
}
