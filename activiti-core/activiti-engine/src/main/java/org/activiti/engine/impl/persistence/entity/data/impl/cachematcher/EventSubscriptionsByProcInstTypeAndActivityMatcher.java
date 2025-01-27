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

package org.activiti.engine.impl.persistence.entity.data.impl.cachematcher;

import org.activiti.engine.impl.persistence.CachedEntityMatcherAdapter;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;

import java.util.Map;

/**
 *
 */
public class EventSubscriptionsByProcInstTypeAndActivityMatcher extends CachedEntityMatcherAdapter<EventSubscriptionEntity> {

    @Override
    public boolean isRetained(EventSubscriptionEntity eventSubscriptionEntity, Object parameter) {

        Map<String, String> params = (Map<String, String>) parameter;
        String type = params.get("eventType");
        String processInstanceId = params.get("processInstanceId");
        String activityId = params.get("activityId");

        return eventSubscriptionEntity.getEventType() != null && eventSubscriptionEntity.getEventType().equals(type)
            && eventSubscriptionEntity.getProcessInstanceId() != null && eventSubscriptionEntity.getProcessInstanceId().equals(processInstanceId)
            && eventSubscriptionEntity.getActivityId() != null && eventSubscriptionEntity.getActivityId().equals(activityId);
    }

}
