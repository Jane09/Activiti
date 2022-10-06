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
package org.activiti.runtime.api.event.impl;

import org.activiti.api.process.runtime.events.ProcessCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiProcessCancelledEvent;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.runtime.api.model.impl.APIProcessInstanceConverter;

import java.util.Optional;

public class ToProcessCancelledConverter implements EventConverter<ProcessCancelledEvent, ActivitiProcessCancelledEvent> {

    private final APIProcessInstanceConverter processInstanceConverter;

    public ToProcessCancelledConverter(APIProcessInstanceConverter processInstanceConverter) {
        this.processInstanceConverter = processInstanceConverter;
    }

    @Override
    public Optional<ProcessCancelledEvent> from(ActivitiProcessCancelledEvent internalEvent) {
        String cause = internalEvent.getCause() != null ? internalEvent.getCause().toString() : null;
        return Optional.of(new ProcessCancelledImpl(
            processInstanceConverter.from((ProcessInstance) internalEvent.getEntity()),
            cause));
    }
}
