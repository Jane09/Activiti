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

import java.io.Serializable;

/**
 *
 */
public class EventSubscriptionDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    protected final String eventName;
    protected final String eventType;

    protected boolean async;
    protected String activityId;
    protected boolean isStartEvent;
    protected String configuration;

    public EventSubscriptionDeclaration(String eventName, String eventType) {
        this.eventName = eventName;
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityId() {
        return activityId;
    }

    public boolean isStartEvent() {
        return isStartEvent;
    }

    public void setStartEvent(boolean isStartEvent) {
        this.isStartEvent = isStartEvent;
    }

    public String getEventType() {
        return eventType;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
