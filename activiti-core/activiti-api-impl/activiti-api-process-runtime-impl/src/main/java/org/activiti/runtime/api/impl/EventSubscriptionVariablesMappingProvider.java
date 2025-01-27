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

import org.activiti.engine.impl.bpmn.behavior.MappingExecutionContext;
import org.activiti.engine.impl.bpmn.behavior.VariablesCalculator;
import org.activiti.engine.impl.event.EventSubscriptionPayloadMappingProvider;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;

import java.util.Map;

public class EventSubscriptionVariablesMappingProvider implements EventSubscriptionPayloadMappingProvider {

    private final VariablesCalculator variablesCalculator;

    public EventSubscriptionVariablesMappingProvider(
        VariablesCalculator variablesCalculator) {
        this.variablesCalculator = variablesCalculator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T apply(Object payload, EventSubscriptionEntity eventSubscription) {
        if (Map.class.isInstance(payload)) {
            MappingExecutionContext context = new MappingExecutionContext(eventSubscription.getProcessDefinitionId(),
                eventSubscription.getActivityId());

            return (T) variablesCalculator.calculateOutPutVariables(context, (Map<String, Object>) payload);
        } else {
            return EventSubscriptionPayloadMappingProvider.super.apply(payload, eventSubscription);
        }
    }

}
