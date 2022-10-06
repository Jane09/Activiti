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


package org.activiti.engine.impl.interceptor;

import org.activiti.engine.api.internal.Internal;

/**
 * 命令连接器，执行命令，可以获取下一个命令拦截器，或者设置下一个拦截器 <br>
 * 责任链模式
 */
@Internal
public interface CommandInterceptor {

    <T> T execute(CommandConfig config, Command<T> command);

    CommandInterceptor getNext();

    void setNext(CommandInterceptor next);

}
