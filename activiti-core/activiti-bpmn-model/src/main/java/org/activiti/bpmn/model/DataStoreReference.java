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
package org.activiti.bpmn.model;

public class DataStoreReference extends FlowElement {

    protected String dataState;
    protected String itemSubjectRef;
    protected String dataStoreRef;

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public String getItemSubjectRef() {
        return itemSubjectRef;
    }

    public void setItemSubjectRef(String itemSubjectRef) {
        this.itemSubjectRef = itemSubjectRef;
    }

    public String getDataStoreRef() {
        return dataStoreRef;
    }

    public void setDataStoreRef(String dataStoreRef) {
        this.dataStoreRef = dataStoreRef;
    }

    public DataStoreReference clone() {
        DataStoreReference clone = new DataStoreReference();
        clone.setValues(this);
        return clone;
    }

    public void setValues(DataStoreReference otherElement) {
        super.setValues(otherElement);
        setDataState(otherElement.getDataState());
        setItemSubjectRef(otherElement.getItemSubjectRef());
        setDataStoreRef(otherElement.getDataStoreRef());
    }

}
