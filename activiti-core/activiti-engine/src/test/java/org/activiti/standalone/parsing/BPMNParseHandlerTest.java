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

package org.activiti.standalone.parsing;

import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.test.Deployment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class BPMNParseHandlerTest extends ResourceActivitiTestCase {

    public BPMNParseHandlerTest() {
        super("org/activiti/standalone/parsing/bpmn.parse.listener.activiti.cfg.xml");
    }

    @Deployment
    public void testAlterProcessDefinitionKeyWhenDeploying() throws Exception {
        // Check if process-definition has different key
        assertThat(repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").count()).isEqualTo(0);
        assertThat(repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess-modified").count()).isEqualTo(1);
    }
}
