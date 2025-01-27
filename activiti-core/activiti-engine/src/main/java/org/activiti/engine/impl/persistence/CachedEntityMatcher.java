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
package org.activiti.engine.impl.persistence;

import org.activiti.engine.api.internal.Internal;
import org.activiti.engine.impl.persistence.cache.CachedEntity;
import org.activiti.engine.impl.persistence.entity.Entity;

import java.util.Collection;

/**
 * Interface to express a condition whether or not a cached entity should be used in the return result of a query.
 */
@Internal
public interface CachedEntityMatcher<EntityImpl extends Entity> {

    /**
     * Returns true if an entity from the cache should be retained (i.e. used as return result for a query).
     * <p>
     * Most implementations of this interface probably don't need this method,
     * and should extend the simpler {@link CachedEntityMatcherAdapter}, which hides this method.
     * <p>
     * Note that the databaseEntities collection can be null, in case only the cache is checked.
     */
    boolean isRetained(Collection<EntityImpl> databaseEntities, Collection<CachedEntity> cachedEntities, EntityImpl entity, Object param);

}
