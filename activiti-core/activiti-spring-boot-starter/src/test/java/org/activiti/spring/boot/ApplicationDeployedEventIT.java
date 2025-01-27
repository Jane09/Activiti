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
package org.activiti.spring.boot;

import org.activiti.api.process.model.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ApplicationDeployedEventIT {
    @Autowired
    private DeployedApplicationListener listener;
    private static final String DEPLOYMENT_TYPE_NAME = "SpringAutoDeployment";

    @Test
    public void shouldTriggerApplicationDeployedEvents() {
        List<Deployment> deployedApplications = listener.getDeployedApplication();

        assertThat(deployedApplications)
            .extracting(Deployment::getName)
            .containsExactly(DEPLOYMENT_TYPE_NAME);
    }
}
