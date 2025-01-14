/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.core.el;

import javax.el.FunctionMapper;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of a {@link FunctionMapper}.
 * <p>
 * A non-null implementation is required by the javax.el.* classes, hence the reason for this pretty useless class.
 */
public class ActivitiFunctionMapper extends FunctionMapper {

    Map<String, Method> map = Collections.emptyMap();

    public Method resolveFunction(String prefix, String localName) {
        return map.get(prefix + ":" + localName);
    }

    public void setFunction(String prefix, String localName, Method method) {
        if (map.isEmpty()) {
            map = new HashMap<String, Method>();
        }
        map.put(prefix + ":" + localName, method);
    }
}
