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
package org.activiti.engine.impl;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

public class NativeTaskQueryImpl extends AbstractNativeQuery<NativeTaskQuery, Task> implements NativeTaskQuery {

    private static final long serialVersionUID = 1L;

    public NativeTaskQueryImpl(CommandContext commandContext) {
        super(commandContext);
    }

    public NativeTaskQueryImpl(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    // results ////////////////////////////////////////////////////////////////

    public List<Task> executeList(CommandContext commandContext, Map<String, Object> parameterMap, int firstResult, int maxResults) {
        return commandContext.getTaskEntityManager().findTasksByNativeQuery(parameterMap, firstResult, maxResults);
    }

    public long executeCount(CommandContext commandContext, Map<String, Object> parameterMap) {
        return commandContext.getTaskEntityManager().findTaskCountByNativeQuery(parameterMap);
    }

}
