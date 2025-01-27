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

package org.activiti.examples.bpmn.event.error;

import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class BoundaryErrorEventTest extends PluggableActivitiTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Normally the UI will do this automatically for us
        Authentication.setAuthenticatedUserId("kermit");
    }

    @Override
    protected void tearDown() throws Exception {
        Authentication.setAuthenticatedUserId(null);
        super.tearDown();
    }

    @Deployment(resources = {"org/activiti/examples/bpmn/event/error/reviewSalesLead.bpmn20.xml"})
    public void testReviewSalesLeadProcess() {

        // After starting the process, a task should be assigned to the
        // 'initiator' (normally set by GUI)
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("details", "very interesting");
        variables.put("customerName", "Alfresco");
        String procId = runtimeService.startProcessInstanceByKey("reviewSaledLead", variables).getId();
        Task task = taskService.createTaskQuery().taskAssignee("kermit").singleResult();
        assertThat(task.getName()).isEqualTo("Provide new sales lead");

        // After completing the task, the review subprocess will be active
        taskService.complete(task.getId());
        Task ratingTask = taskService.createTaskQuery().taskCandidateGroup("accountancy").singleResult();
        assertThat(ratingTask.getName()).isEqualTo("Review customer rating");
        Task profitabilityTask = taskService.createTaskQuery().taskCandidateGroup("management").singleResult();
        assertThat(profitabilityTask.getName()).isEqualTo("Review profitability");

        // Complete the management task by stating that not enough info was
        // provided
        // This should throw the error event, which closes the subprocess
        variables = new HashMap<String, Object>();
        variables.put("notEnoughInformation", true);
        taskService.complete(profitabilityTask.getId(), variables);

        // The 'provide additional details' task should now be active
        Task provideDetailsTask = taskService.createTaskQuery().taskAssignee("kermit").singleResult();
        assertThat(provideDetailsTask.getName()).isEqualTo("Provide additional details");

        // Providing more details (ie. completing the task), will activate the
        // subprocess again
        taskService.complete(provideDetailsTask.getId());
        List<Task> reviewTasks = taskService.createTaskQuery().orderByTaskName().asc().list();
        assertThat(reviewTasks.get(0).getName()).isEqualTo("Review customer rating");
        assertThat(reviewTasks.get(1).getName()).isEqualTo("Review profitability");

        // Completing both tasks normally ends the process
        taskService.complete(reviewTasks.get(0).getId());
        variables.put("notEnoughInformation", false);
        taskService.complete(reviewTasks.get(1).getId(), variables);
        assertProcessEnded(procId);
    }

}
