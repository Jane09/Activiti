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


package org.activiti.examples.bpmn.executionlistener;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RecorderExecutionListener implements ExecutionListener {

    private static final long serialVersionUID = 1L;

    private FixedValue parameter;

    private static List<RecorderExecutionListener.RecordedEvent> recordedEvents = new ArrayList<RecorderExecutionListener.RecordedEvent>();

    public static class RecordedEvent {
        private final String activityId;
        private final String eventName;
        private final String activityName;
        private final String parameter;

        public RecordedEvent(String activityId, String activityName, String eventName, String parameter) {
            this.activityId = activityId;
            this.activityName = activityName;
            this.parameter = parameter;
            this.eventName = eventName;
        }

        public String getActivityId() {
            return activityId;
        }

        public String getEventName() {
            return eventName;
        }

        public String getActivityName() {
            return activityName;
        }

        public String getParameter() {
            return parameter;
        }

    }

    public void notify(DelegateExecution execution) {
        ExecutionEntity executionCasted = ((ExecutionEntity) execution);

        org.activiti.bpmn.model.Process process = ProcessDefinitionUtil.getProcess(execution.getProcessDefinitionId());
        String activityId = execution.getCurrentActivityId();
        FlowElement currentFlowElement = process.getFlowElement(activityId, true);

        recordedEvents.add(new RecordedEvent(
            executionCasted.getActivityId(),
            (currentFlowElement != null) ? currentFlowElement.getName() : null,
            execution.getEventName(),
            (String) parameter.getValue(execution)));
    }

    public static void clear() {
        recordedEvents.clear();
    }

    public static List<RecordedEvent> getRecordedEvents() {
        return recordedEvents;
    }

}
