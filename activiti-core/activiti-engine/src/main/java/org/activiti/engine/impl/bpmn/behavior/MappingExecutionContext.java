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
package org.activiti.engine.impl.bpmn.behavior;

import org.activiti.engine.delegate.DelegateExecution;

import java.util.Objects;

public class MappingExecutionContext {

    private String processDefinitionId;
    private String activityId;
    private DelegateExecution execution;

    public MappingExecutionContext(DelegateExecution delegateExecution) {
        this.processDefinitionId = delegateExecution.getProcessDefinitionId();
        this.activityId = delegateExecution.getCurrentActivityId();
        this.execution = delegateExecution;
    }

    public MappingExecutionContext(String processDefinitionId,
                                   String activityId) {
        this.processDefinitionId = processDefinitionId;
        this.activityId = activityId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public String getActivityId() {
        return activityId;
    }

    public boolean hasExecution() {
        return this.execution != null;
    }

    public DelegateExecution getExecution() {
        return execution;
    }

    public static MappingExecutionContext buildMappingExecutionContext(DelegateExecution delegateExecution) {
        return new MappingExecutionContext(delegateExecution);
    }

    public static MappingExecutionContext buildMappingExecutionContext(String processDefinitionId,
                                                                       String activityId) {
        return new MappingExecutionContext(processDefinitionId,
            activityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MappingExecutionContext that = (MappingExecutionContext) o;
        return Objects.equals(processDefinitionId,
            that.processDefinitionId) &&
            Objects.equals(activityId,
                that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processDefinitionId,
            activityId);
    }
}
