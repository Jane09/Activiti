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


package org.activiti.standalone.interceptor;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandContextIT extends PluggableActivitiTestCase {

    public void testCommandContextGetCurrentAfterException() {
        try {
            processEngineConfiguration.getCommandExecutor().execute(new Command<Object>() {
                public Object execute(CommandContext commandContext) {
                    throw new IllegalStateException("here i come!");
                }
            });

            fail("expected exception");
        } catch (IllegalStateException e) {
            // OK
        }

        assertThat(Context.getCommandContext()).isNull();
    }
}
