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

package org.activiti.engine.impl.transformer;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * Transforms a {@link String} to a {@link Date}
 */
public class StringToDate extends AbstractTransformer {

    protected FastDateFormat format = FastDateFormat.getInstance("dd/MM/yyyy");

    @Override
    protected Object primTransform(Object anObject) throws Exception {
        return format.parse((String) anObject);
    }
}
