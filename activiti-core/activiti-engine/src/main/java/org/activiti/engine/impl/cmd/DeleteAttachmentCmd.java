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


package org.activiti.engine.impl.cmd;

import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.AttachmentEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import java.io.Serializable;

/**
 *
 */
public class DeleteAttachmentCmd implements Command<Object>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String attachmentId;

    public DeleteAttachmentCmd(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Object execute(CommandContext commandContext) {
        AttachmentEntity attachment = commandContext.getAttachmentEntityManager().findById(attachmentId);

        String processInstanceId = attachment.getProcessInstanceId();
        String processDefinitionId = null;
        if (attachment.getProcessInstanceId() != null) {
            ExecutionEntity process = commandContext.getExecutionEntityManager().findById(processInstanceId);
            if (process != null) {
                processDefinitionId = process.getProcessDefinitionId();
            }
        }
        executeInternal(commandContext, attachment, processInstanceId, processDefinitionId);

        return null;
    }

    protected void executeInternal(CommandContext commandContext, AttachmentEntity attachment, String processInstanceId, String processDefinitionId) {
        commandContext.getAttachmentEntityManager().delete(attachment, false);

        if (attachment.getContentId() != null) {
            commandContext.getByteArrayEntityManager().deleteByteArrayById(attachment.getContentId());
        }

        if (attachment.getTaskId() != null) {
            commandContext.getHistoryManager().createAttachmentComment(attachment.getTaskId(), attachment.getProcessInstanceId(), attachment.getName(), false);
        }

        if (commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            commandContext.getProcessEngineConfiguration().getEventDispatcher()
                .dispatchEvent(ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_DELETED, attachment, processInstanceId, processInstanceId, processDefinitionId));
        }
    }

}
