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

package org.activiti.validation.validator.impl;

import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.validation.ValidationError;
import org.activiti.validation.validator.Problems;
import org.activiti.validation.validator.ProcessLevelValidator;

import java.util.List;

/**
 *
 */
public class EndEventValidator extends ProcessLevelValidator {

    @Override
    protected void executeValidation(BpmnModel bpmnModel, Process process, List<ValidationError> errors) {
        List<EndEvent> endEvents = process.findFlowElementsOfType(EndEvent.class);
        for (EndEvent endEvent : endEvents) {
            if (endEvent.getEventDefinitions() != null && !endEvent.getEventDefinitions().isEmpty()) {

                EventDefinition eventDefinition = endEvent.getEventDefinitions().get(0);

                // Error end event
                if (eventDefinition instanceof CancelEventDefinition) {

                    FlowElementsContainer parent = process.findParent(endEvent);
                    if (!(parent instanceof Transaction)) {
                        addError(errors, Problems.END_EVENT_CANCEL_ONLY_INSIDE_TRANSACTION, process, endEvent, "end event with cancelEventDefinition only supported inside transaction subprocess");
                    }

                }

            }
        }
    }

}
