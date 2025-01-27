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


package org.activiti.engine.impl.variable;

/**
 * Subclass of {@link JPAEntityListVariableType} which is cacheable, unlike the super-class. This is used when fetching historic variables
 */
public class HistoricJPAEntityListVariableType extends JPAEntityListVariableType {

    private static final HistoricJPAEntityListVariableType INSTANCE = new HistoricJPAEntityListVariableType();

    @Override
    public boolean isCachable() {
        return true;
    }

    public static HistoricJPAEntityListVariableType getSharedInstance() {
        return INSTANCE;
    }

}
