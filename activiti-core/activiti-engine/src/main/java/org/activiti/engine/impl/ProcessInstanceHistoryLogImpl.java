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
package org.activiti.engine.impl;

import org.activiti.engine.history.HistoricData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;

import java.util.*;

/**
 *
 */
public class ProcessInstanceHistoryLogImpl implements ProcessInstanceHistoryLog {

    protected HistoricProcessInstance historicProcessInstance;

    protected List<HistoricData> historicData = new ArrayList<HistoricData>();

    public ProcessInstanceHistoryLogImpl(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    @Override
    public String getId() {
        return historicProcessInstance.getId();
    }

    @Override
    public String getBusinessKey() {
        return historicProcessInstance.getBusinessKey();
    }

    @Override
    public String getProcessDefinitionId() {
        return historicProcessInstance.getProcessDefinitionId();
    }

    @Override
    public Date getStartTime() {
        return historicProcessInstance.getStartTime();
    }

    @Override
    public Date getEndTime() {
        return historicProcessInstance.getEndTime();
    }

    @Override
    public Long getDurationInMillis() {
        return historicProcessInstance.getDurationInMillis();
    }

    @Override
    public String getStartUserId() {
        return historicProcessInstance.getStartUserId();
    }

    @Override
    public String getStartActivityId() {
        return historicProcessInstance.getStartActivityId();
    }

    @Override
    public String getDeleteReason() {
        return historicProcessInstance.getDeleteReason();
    }

    @Override
    public String getSuperProcessInstanceId() {
        return historicProcessInstance.getSuperProcessInstanceId();
    }

    @Override
    public String getTenantId() {
        return historicProcessInstance.getTenantId();
    }

    @Override
    public List<HistoricData> getHistoricData() {
        return historicData;
    }

    public void addHistoricData(HistoricData historicEvent) {
        historicData.add(historicEvent);
    }

    public void addHistoricData(Collection<? extends HistoricData> historicEvents) {
        historicData.addAll(historicEvents);
    }

    public void orderHistoricData() {
        Collections.sort(historicData, new Comparator<HistoricData>() {
            @Override
            public int compare(HistoricData data1, HistoricData data2) {
                return data1.getTime().compareTo(data2.getTime());
            }
        });
    }

}
