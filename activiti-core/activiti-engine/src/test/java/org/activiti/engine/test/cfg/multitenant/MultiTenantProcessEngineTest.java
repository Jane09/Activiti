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


package org.activiti.engine.test.cfg.multitenant;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.asyncexecutor.multitenant.ExecutorPerTenantAsyncExecutor;
import org.activiti.engine.impl.asyncexecutor.multitenant.SharedExecutorServiceAsyncExecutor;
import org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 *
 */
public class MultiTenantProcessEngineTest {

    private DummyTenantInfoHolder tenantInfoHolder;
    private MultiSchemaMultiTenantProcessEngineConfiguration config;
    private ProcessEngine processEngine;

    @Before
    public void setup() {
        setupTenantInfoHolder();
    }

    @After
    public void close() {
        processEngine.close();
    }

    private void setupTenantInfoHolder() {
        DummyTenantInfoHolder tenantInfoHolder = new DummyTenantInfoHolder();

        tenantInfoHolder.addTenant("alfresco");
        tenantInfoHolder.addUser("alfresco", "joram");
        tenantInfoHolder.addUser("alfresco", "tijs");
        tenantInfoHolder.addUser("alfresco", "paul");
        tenantInfoHolder.addUser("alfresco", "yvo");

        tenantInfoHolder.addTenant("acme");
        tenantInfoHolder.addUser("acme", "raphael");
        tenantInfoHolder.addUser("acme", "john");

        tenantInfoHolder.addTenant("starkindustries");
        tenantInfoHolder.addUser("starkindustries", "tony");

        this.tenantInfoHolder = tenantInfoHolder;
    }

    private void setupProcessEngine(boolean sharedExecutor) {
        config = new MultiSchemaMultiTenantProcessEngineConfiguration(tenantInfoHolder);

        config.setDatabaseType(MultiSchemaMultiTenantProcessEngineConfiguration.DATABASE_TYPE_H2);
        config.setDatabaseSchemaUpdate(MultiSchemaMultiTenantProcessEngineConfiguration.DB_SCHEMA_UPDATE_DROP_CREATE);

        config.setAsyncExecutorActivate(true);

        if (sharedExecutor) {
            config.setAsyncExecutor(new SharedExecutorServiceAsyncExecutor(tenantInfoHolder));
        } else {
            config.setAsyncExecutor(new ExecutorPerTenantAsyncExecutor(tenantInfoHolder));
        }

        config.registerTenant("alfresco", createDataSource("jdbc:h2:mem:activiti-mt-alfresco;DB_CLOSE_DELAY=1000", "sa", ""));
        config.registerTenant("acme", createDataSource("jdbc:h2:mem:activiti-mt-acme;DB_CLOSE_DELAY=1000", "sa", ""));
        config.registerTenant("starkindustries", createDataSource("jdbc:h2:mem:activiti-mt-stark;DB_CLOSE_DELAY=1000", "sa", ""));


        processEngine = config.buildProcessEngine();
    }

    @Test
    public void testStartProcessInstancesWithSharedExecutor() throws Exception {
        setupProcessEngine(true);
        runProcessInstanceTest();
    }

    @Test
    public void testStartProcessInstancesWithExecutorPerTenantAsyncExecutor() throws Exception {
        setupProcessEngine(false);
        runProcessInstanceTest();
    }

    protected void runProcessInstanceTest() throws InterruptedException {
        // deploy processes per user
        deployProcesses("joram");
        deployProcesses("raphael");
        deployProcesses("tony");

        // Generate data
        startProcessInstances("joram");
        startProcessInstances("joram");
        startProcessInstances("joram");
        startProcessInstances("raphael");
        startProcessInstances("raphael");
        completeTasks("raphael");
        startProcessInstances("tony");

        // Verify
        assertData("joram", 9, 3);
        assertData("raphael", 2, 2);
        assertData("tony", 3, 1);

        // Adding a new tenant
        tenantInfoHolder.addTenant("dailyplanet");
        tenantInfoHolder.addUser("dailyplanet", "louis");
        tenantInfoHolder.addUser("dailyplanet", "clark");

        config.registerTenant("dailyplanet", createDataSource("jdbc:h2:mem:activiti-mt-daily;DB_CLOSE_DELAY=1000", "sa", ""));

        deployProcesses("louis");
        deployProcesses("clark");

        // Start process instance for new tenant
        startProcessInstances("clark");
        startProcessInstances("clark");
        assertData("clark", 6, 2);

        moveClockToGetTimerFired();
        await().atMost(15, TimeUnit.SECONDS).untilAsserted(() -> {

            assertData("joram", 9, 0);
            assertData("raphael", 2, 0);
            assertData("tony", 3, 0);
            assertData("clark", 6, 0);
        });
        assertExecutionReachesTaskAfterTimer();
    }

    private void assertExecutionReachesTaskAfterTimer() {
        await().untilAsserted(() ->
            assertThat(getTasks("raphael", "TimerJob_test"))
                .extracting(Task::getName)
                .containsOnly("second form")
        );
    }

    private void moveClockToGetTimerFired() {
        config.getClock().setCurrentTime(new Date(config.getClock().getCurrentTime().getTime() + (2 * 60 * 60 * 1000)));
    }


    private void deployProcesses(String userId) {
        tenantInfoHolder.setCurrentUserId(userId);

        Deployment deployment = processEngine.getRepositoryService().createDeployment()
            .addClasspathResource("org/activiti/engine/test/cfg/multitenant/oneTaskProcess.bpmn20.xml")
            .addClasspathResource("org/activiti/engine/test/cfg/multitenant/jobTest.bpmn20.xml")
            .addClasspathResource("org/activiti/engine/test/cfg/multitenant/TimerJob_test.bpmn20.xml")
            .deploy();
        System.out.println("Process deployed! Deployment id is " + deployment.getId());

        tenantInfoHolder.clearCurrentUserId();
        tenantInfoHolder.clearCurrentTenantId();
    }

    private void startProcessInstance(String userId, String processDefinitionKey, Map<String, Object> vars) {
        tenantInfoHolder.setCurrentUserId(userId);

        processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, vars);

        tenantInfoHolder.clearCurrentUserId();
        tenantInfoHolder.clearCurrentTenantId();
    }

    private void startProcessInstances(String userId) {
        startProcessInstance(userId, "oneTaskProcess", Map.of("data", "Hello from " + userId));
        startProcessInstance(userId, "jobTest", null);
        startProcessInstance(userId, "TimerJob_test", Map.of("name", "some test from " + userId, "time", "PT1M"));
    }

    private void completeTasks(String userId) {
        tenantInfoHolder.setCurrentUserId(userId);

        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        for (Task task : taskQuery.list()) {
            processEngine.getTaskService().complete(task.getId());
        }

        tenantInfoHolder.clearCurrentUserId();
        tenantInfoHolder.clearCurrentTenantId();
    }

    private List<Task> getTasks(String userId, String processDefinitionKey) {
        tenantInfoHolder.setCurrentUserId(userId);

        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery().processDefinitionKey(processDefinitionKey);
        final List<Task> tasks = taskQuery.list();

        tenantInfoHolder.clearCurrentUserId();
        tenantInfoHolder.clearCurrentTenantId();
        return tasks;
    }

    private void assertData(String userId, long nrOfActiveProcessInstances, long nrOfActiveJobs) {
        tenantInfoHolder.setCurrentUserId(userId);

        assertThat(processEngine.getRuntimeService().createExecutionQuery().onlyProcessInstanceExecutions().count()).isEqualTo(nrOfActiveProcessInstances);
        assertThat(processEngine.getHistoryService().createHistoricProcessInstanceQuery().unfinished().count()).isEqualTo(nrOfActiveProcessInstances);
        assertThat(processEngine.getManagementService().createTimerJobQuery().count()).isEqualTo(nrOfActiveJobs);

        tenantInfoHolder.clearCurrentUserId();
        tenantInfoHolder.clearCurrentTenantId();
    }

    // Helper //////////////////////////////////////////


    private DataSource createDataSource(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(jdbcUrl);
        ds.setUser(jdbcUsername);
        ds.setPassword(jdbcPassword);
        return ds;
    }

}
