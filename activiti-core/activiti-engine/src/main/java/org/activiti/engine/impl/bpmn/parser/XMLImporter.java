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

package org.activiti.engine.impl.bpmn.parser;

import org.activiti.bpmn.model.Import;
import org.activiti.engine.api.internal.Internal;
import org.activiti.engine.impl.bpmn.data.StructureDefinition;
import org.activiti.engine.impl.webservice.WSOperation;
import org.activiti.engine.impl.webservice.WSService;

import java.util.Map;

/**
 * A XML importer
 */
@Internal
public interface XMLImporter {

    /**
     * Imports the definitions in the XML declared in element
     *
     * @param element the declarations to be imported
     */
    void importFrom(Import theImport, String sourceSystemId);

    Map<String, StructureDefinition> getStructures();

    Map<String, WSService> getServices();

    Map<String, WSOperation> getOperations();
}
