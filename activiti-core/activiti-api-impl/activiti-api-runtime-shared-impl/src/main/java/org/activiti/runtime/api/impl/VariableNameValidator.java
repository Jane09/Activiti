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
package org.activiti.runtime.api.impl;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class VariableNameValidator {

    public static String regexPattern = "(?i)[a-z][a-z0-9_]*";

    public boolean validate(String name) {

        if (StringUtils.hasLength(name)) {
            if (Pattern.compile(regexPattern).matcher(name).matches()) {
                return true;
            }
        }
        return false;

    }

    public Set<String> validateVariables(Map<String, Object> variables) {
        Set<String> mismatchedVars = new HashSet<>();
        if (variables != null && !variables.isEmpty()) {
            for (Map.Entry<String, Object> variable : variables.entrySet()) {
                if (!validate(variable.getKey())) {
                    mismatchedVars.add(variable.getKey());
                }
            }
        }

        return mismatchedVars;
    }

}
