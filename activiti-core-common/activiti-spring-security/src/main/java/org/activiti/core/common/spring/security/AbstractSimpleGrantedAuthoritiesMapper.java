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
package org.activiti.core.common.spring.security;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSimpleGrantedAuthoritiesMapper {

    protected static List<String> getAuthoritesFilteredByPrefix(@NonNull Collection<? extends GrantedAuthority> authorities,
                                                                @NonNull String prefix) {
        return authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .filter(a -> a.startsWith(prefix))
            .map(a -> a.substring(prefix.length()))
            .collect(Collectors.collectingAndThen(Collectors.toList(),
                Collections::unmodifiableList));
    }
}
