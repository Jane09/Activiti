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

package org.activiti.examples.bpmn.gateway;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Example of using the exclusive gateway.
 */
public class ExclusiveGatewayTest extends PluggableActivitiTestCase {

    /**
     * The test process has an XOR gateway where, the 'input' variable is used to select one of the outgoing sequence flow. Every one of those sequence flow goes to another task, allowing us to test the
     * decision very easily.
     */
    @Deployment
    public void testDecisionFunctionality() {

        Map<String, Object> variables = new HashMap<String, Object>();

        // Test with input == 1
        variables.put("input", 1);
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("exclusiveGateway", variables);
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertThat(task.getName()).isEqualTo("Send e-mail for more information");

        // Test with input == 2
        variables.put("input", 2);
        pi = runtimeService.startProcessInstanceByKey("exclusiveGateway", variables);
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertThat(task.getName()).isEqualTo("Check account balance");

        // Test with input == 3
        variables.put("input", 3);
        pi = runtimeService.startProcessInstanceByKey("exclusiveGateway", variables);
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        assertThat(task.getName()).isEqualTo("Call customer");

        // Test with input == 4
        variables.put("input", 4);
        // Exception is expected since no outgoing sequence flow matches
        assertThatExceptionOfType(ActivitiException.class)
            .isThrownBy(() -> runtimeService.startProcessInstanceByKey("exclusiveGateway", variables));
    }

}
