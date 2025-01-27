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

package org.activiti.engine.impl.bpmn.helper;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;
import org.activiti.engine.impl.cfg.DelegateExpressionFieldInjectionMode;
import org.activiti.engine.impl.context.Context;

import java.util.List;

/**
 *
 */
public class DelegateExpressionUtil {

    public static Object resolveDelegateExpression(Expression expression, VariableScope variableScope) {
        return resolveDelegateExpression(expression, variableScope, null);
    }

    public static Object resolveDelegateExpression(Expression expression,
                                                   VariableScope variableScope, List<FieldDeclaration> fieldDeclarations) {

        // Note: we can't cache the result of the expression, because the
        // execution can change: eg. delegateExpression='${mySpringBeanFactory.randomSpringBean()}'
        Object delegate = expression.getValue(variableScope);

        if (fieldDeclarations != null && fieldDeclarations.size() > 0) {

            DelegateExpressionFieldInjectionMode injectionMode = Context.getProcessEngineConfiguration().getDelegateExpressionFieldInjectionMode();
            if (injectionMode.equals(DelegateExpressionFieldInjectionMode.COMPATIBILITY)) {
                ClassDelegate.applyFieldDeclaration(fieldDeclarations, delegate, true);
            } else if (injectionMode.equals(DelegateExpressionFieldInjectionMode.MIXED)) {
                ClassDelegate.applyFieldDeclaration(fieldDeclarations, delegate, false);
            }

        }

        return delegate;
    }

}
