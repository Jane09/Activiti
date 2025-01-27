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
package org.activiti.api.runtime.model.impl;

import org.activiti.api.process.model.IntegrationContext;

import java.util.*;

public class IntegrationContextImpl implements IntegrationContext {

    private String id;
    private ProcessVariablesMap<String, Object> inBoundVariables = new ProcessVariablesMap<>();
    private ProcessVariablesMap<String, Object> outBoundVariables = new ProcessVariablesMap<>();
    private String processInstanceId;
    private String parentProcessInstanceId;
    private String rootProcessInstanceId;
    private String processDefinitionId;
    private String executionId;
    private String processDefinitionKey;
    private Integer processDefinitionVersion;
    private String businessKey;
    private String clientId;
    private String clientName;
    private String clientType;
    private String appVersion;
    private String connectorType;

    public IntegrationContextImpl() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getRootProcessInstanceId() {
        return rootProcessInstanceId;
    }

    public void setRootProcessInstanceId(String rootProcessInstanceId) {
        this.rootProcessInstanceId = rootProcessInstanceId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    @Override
    public Map<String, Object> getInBoundVariables() {
        return inBoundVariables;
    }

    public void addInBoundVariables(Map<String, Object> inboundVariables) {
        this.inBoundVariables.putAll(inboundVariables);
    }

    public void addInBoundVariable(String name, Object value) {
        this.inBoundVariables.put(name, value);
    }

    @Override
    public Map<String, Object> getOutBoundVariables() {
        return outBoundVariables;
    }

    @Override
    public void addOutBoundVariable(String name,
                                    Object value) {
        outBoundVariables.put(name, value);
    }

    @Override
    public void addOutBoundVariables(Map<String, Object> variables) {
        outBoundVariables.putAll(variables);
    }

    @Override
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    @Override
    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    @Override
    public String getClientName() {
        return clientName;
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String getClientType() {
        return clientType;
    }


    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


    @Override
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @Override
    public String getParentProcessInstanceId() {
        return parentProcessInstanceId;
    }

    public void setParentProcessInstanceId(String parentProcessInstanceId) {
        this.parentProcessInstanceId = parentProcessInstanceId;
    }

    @Override
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    @Override
    public String getExecutionId() {
        return executionId;
    }


    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appVersion,
            businessKey,
            clientId,
            clientName,
            clientType,
            connectorType,
            executionId,
            id,
            inBoundVariables,
            outBoundVariables,
            parentProcessInstanceId,
            processDefinitionId,
            processDefinitionKey,
            processDefinitionVersion,
            processInstanceId,
            rootProcessInstanceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IntegrationContextImpl other = (IntegrationContextImpl) obj;
        return Objects.equals(appVersion, other.appVersion) &&
            Objects.equals(businessKey, other.businessKey) &&
            Objects.equals(clientId, other.clientId) &&
            Objects.equals(clientName, other.clientName) &&
            Objects.equals(clientType, other.clientType) &&
            Objects.equals(connectorType, other.connectorType) &&
            Objects.equals(executionId, other.executionId) &&
            Objects.equals(id, other.id) &&
            Objects.equals(inBoundVariables, other.inBoundVariables) &&
            Objects.equals(outBoundVariables, other.outBoundVariables) &&
            Objects.equals(parentProcessInstanceId, other.parentProcessInstanceId) &&
            Objects.equals(processDefinitionId, other.processDefinitionId) &&
            Objects.equals(processDefinitionKey, other.processDefinitionKey) &&
            Objects.equals(processDefinitionVersion, other.processDefinitionVersion) &&
            Objects.equals(processInstanceId, other.processInstanceId) &&
            Objects.equals(rootProcessInstanceId, other.rootProcessInstanceId);
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        StringBuilder builder = new StringBuilder();
        builder.append("IntegrationContextImpl [id=")
            .append(id)
            .append(", inboundVariables=")
            .append(inBoundVariables != null ? toString(inBoundVariables.entrySet(), maxLen) : null)
            .append(", outBoundVariables=")
            .append(outBoundVariables != null ? toString(outBoundVariables.entrySet(), maxLen) : null)
            .append(", rootProcessInstanceId=")
            .append(rootProcessInstanceId)
            .append(", processInstanceId=")
            .append(processInstanceId)
            .append(", parentProcessInstanceId=")
            .append(parentProcessInstanceId)
            .append(", processDefinitionId=")
            .append(processDefinitionId)
            .append(", executionId=")
            .append(executionId)
            .append(", processDefinitionKey=")
            .append(processDefinitionKey)
            .append(", processDefinitionVersion=")
            .append(processDefinitionVersion)
            .append(", businessKey=")
            .append(businessKey)
            .append(", clientId=")
            .append(clientId)
            .append(", clientName=")
            .append(clientName)
            .append(", clientType=")
            .append(clientType)
            .append(", appVersion=")
            .append(appVersion)
            .append(", connectorType=")
            .append(connectorType)
            .append("]");
        return builder.toString();
    }

    private String toString(Collection<?> collection, int maxLen) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(iterator.next());
        }
        builder.append("]");
        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInBoundVariable(String name) {
        return Optional.ofNullable(inBoundVariables)
            .map(it -> (T) inBoundVariables.get(name))
            .orElse(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInBoundVariable(String name, Class<T> type) {
        return Optional.ofNullable(inBoundVariables)
            .map(it -> (T) inBoundVariables.get(name))
            .orElse(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOutBoundVariable(String name) {
        return Optional.ofNullable(outBoundVariables)
            .map(it -> (T) it.get(name))
            .orElse(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOutBoundVariable(String name, Class<T> type) {
        return Optional.ofNullable(outBoundVariables)
            .map(it -> (T) it.get(name))
            .orElse(null);
    }

}
