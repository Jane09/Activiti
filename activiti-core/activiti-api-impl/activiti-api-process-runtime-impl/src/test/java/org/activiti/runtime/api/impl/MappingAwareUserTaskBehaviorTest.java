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

import org.activiti.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MappingAwareUserTaskBehaviorTest {

    @InjectMocks
    private MappingAwareUserTaskBehavior behavior;

    @Mock
    private ExtensionsVariablesMappingProvider mappingProvider;

    @Test
    public void calculateInputVariablesShouldReturnValueFromMappingProvider() {
        //given
        DelegateExecution execution = buildExecution();
        Map<String, Object> providerVariables = singletonMap("var", "value");
        given(mappingProvider.calculateInputVariables(execution)).willReturn(providerVariables);

        //when
        Map<String, Object> inputVariables = behavior.calculateInputVariables(execution);

        //then
        assertThat(inputVariables).isEqualTo(providerVariables);
    }

    private DelegateExecution buildExecution() {
        return mock(DelegateExecution.class);
    }
}
