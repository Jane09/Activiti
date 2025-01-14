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


package org.activiti.engine.test.bpmn.sequenceflow;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.bpmn.gateway.ExclusiveGatewayTest;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * See {@link ExclusiveGatewayTest} for a default sequence flow test on an exclusive gateway.
 */
public class DefaultSequenceFlowTest extends PluggableActivitiTestCase {

    @Deployment
    public void testDefaultSequenceFlowOnTask() {
        String procId = runtimeService.startProcessInstanceByKey("defaultSeqFlow", singletonMap("input", 2)).getId();
        assertThat(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task2").singleResult()).isNotNull();

        procId = runtimeService.startProcessInstanceByKey("defaultSeqFlow", singletonMap("input", 3)).getId();
        assertThat(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task3").singleResult()).isNotNull();

        procId = runtimeService.startProcessInstanceByKey("defaultSeqFlow", singletonMap("input", 123)).getId();
        assertThat(runtimeService.createExecutionQuery().processInstanceId(procId).activityId("task1").singleResult()).isNotNull();
    }

}
