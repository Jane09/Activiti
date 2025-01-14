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


package org.activiti.spring.process.conf;

import org.activiti.spring.process.ProcessExtensionResourceFinderDescriptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessExtensionResourceFinderDescriptorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ProcessExtensionResourceFinderDescriptor processExtensionResourceFinderDescriptor(
        @Value("${spring.activiti.process.extensions.dir:classpath*:**/processes/}") String locationPrefix,
        @Value("${spring.activiti.process.extensions.suffix:**-extensions.json}") String locationSuffix) {
        return new ProcessExtensionResourceFinderDescriptor(true,
            locationPrefix,
            locationSuffix);
    }
}
