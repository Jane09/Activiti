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

package org.activiti.engine.test.bpmn.event;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.bpmn.model.Message;
import org.activiti.bpmn.model.MessageEventDefinition;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.MessageEventDefinitionParseHandler;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

public class MessageEventDefinitionWithExtensionElementsTest {

    @Test
    public void testParseMessagedDefinitionWithExtension() {
        BpmnParse bpmnParseMock = Mockito.mock(BpmnParse.class);
        MessageEventDefinition messageEventDefinitionMock = Mockito.mock(MessageEventDefinition.class);
        BpmnModel bpmnModelMock = Mockito.mock(BpmnModel.class);
        Message messageMock = Mockito.mock(Message.class);
        @SuppressWarnings("unchecked")
        Map<String, List<ExtensionElement>> extensionElementMap = Mockito.mock(Map.class);

        Mockito.when(bpmnParseMock.getBpmnModel()).thenReturn(bpmnModelMock);
        Mockito.when(messageEventDefinitionMock.getMessageRef()).thenReturn("messageId");
        Mockito.when(bpmnModelMock.containsMessageId("messageId")).thenReturn(true);
        Mockito.when(bpmnModelMock.getMessage("messageId")).thenReturn(messageMock);
        Mockito.when(messageMock.getName()).thenReturn("MessageWithExtensionElements");
        Mockito.when(messageMock.getExtensionElements()).thenReturn(extensionElementMap);

        MessageEventDefinitionParseHandler handler = new MessageEventDefinitionParseHandler();
        handler.parse(bpmnParseMock, messageEventDefinitionMock);

        Mockito.verify(messageEventDefinitionMock).setMessageRef("MessageWithExtensionElements");
        Mockito.verify(messageEventDefinitionMock).setExtensionElements(extensionElementMap);
    }
}
