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

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.ValuedDataObject;
import org.activiti.validation.ValidationError;
import org.activiti.validation.validator.Problems;
import org.activiti.validation.validator.ProcessLevelValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DataObjectValidator extends ProcessLevelValidator {

    @Override
    protected void executeValidation(BpmnModel bpmnModel, Process process, List<ValidationError> errors) {

        // Gather data objects
        List<ValuedDataObject> allDataObjects = new ArrayList<ValuedDataObject>();
        allDataObjects.addAll(process.getDataObjects());
        List<SubProcess> subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
        for (SubProcess subProcess : subProcesses) {
            allDataObjects.addAll(subProcess.getDataObjects());
        }

        // Validate
        for (ValuedDataObject dataObject : allDataObjects) {
            if (StringUtils.isEmpty(dataObject.getName())) {
                addError(errors, Problems.DATA_OBJECT_MISSING_NAME, process, dataObject, "Name is mandatory for a data object");
            }
        }

    }

}
