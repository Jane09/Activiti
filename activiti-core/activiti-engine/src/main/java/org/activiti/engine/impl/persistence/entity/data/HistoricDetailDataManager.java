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

package org.activiti.engine.impl.persistence.entity.data;

import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.impl.HistoricDetailQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.HistoricDetailAssignmentEntity;
import org.activiti.engine.impl.persistence.entity.HistoricDetailEntity;
import org.activiti.engine.impl.persistence.entity.HistoricDetailTransitionInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;

import java.util.List;
import java.util.Map;


public interface HistoricDetailDataManager extends DataManager<HistoricDetailEntity> {

    HistoricDetailAssignmentEntity createHistoricDetailAssignment();

    HistoricDetailTransitionInstanceEntity createHistoricDetailTransitionInstance();

    HistoricDetailVariableInstanceUpdateEntity createHistoricDetailVariableInstanceUpdate();

    List<HistoricDetailEntity> findHistoricDetailsByProcessInstanceId(String processInstanceId);

    List<HistoricDetailEntity> findHistoricDetailsByTaskId(String taskId);

    long findHistoricDetailCountByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery);

    List<HistoricDetail> findHistoricDetailsByQueryCriteria(HistoricDetailQueryImpl historicVariableUpdateQuery, Page page);

    List<HistoricDetail> findHistoricDetailsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults);

    long findHistoricDetailCountByNativeQuery(Map<String, Object> parameterMap);

}
