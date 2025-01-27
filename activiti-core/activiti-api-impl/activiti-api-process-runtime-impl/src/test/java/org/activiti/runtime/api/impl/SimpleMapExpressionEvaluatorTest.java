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

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.interceptor.DelegateInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SimpleMapExpressionEvaluatorTest {

    @Mock
    private ExpressionManager expressionManager;

    @Mock
    private DelegateInterceptor delegateInterceptor;

    @Test
    public void evaluate_should_returnResultOfGetValueWithMap() {
        //given
        Map<String, Object> context = singletonMap("city", "London");
        SimpleMapExpressionEvaluator evaluator = new SimpleMapExpressionEvaluator(
            context);
        Expression expression = mock(Expression.class);

        given(expression.getValue(expressionManager, delegateInterceptor, context)).willReturn("London");

        //when
        Object value = evaluator.evaluate(expression, expressionManager, delegateInterceptor);

        //then
        assertThat(value).isEqualTo("London");
    }
}
