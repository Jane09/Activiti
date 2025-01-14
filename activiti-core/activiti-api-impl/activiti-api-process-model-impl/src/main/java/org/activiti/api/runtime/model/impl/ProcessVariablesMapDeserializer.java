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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

import java.io.IOException;

public class ProcessVariablesMapDeserializer extends JsonDeserializer<ProcessVariablesMap<String, Object>> {
    private static final Logger logger = LoggerFactory.getLogger(ProcessVariablesMapDeserializer.class);

    private static final String VALUE = "value";
    private static final String TYPE = "type";
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final ConversionService conversionService;

    public ProcessVariablesMapDeserializer(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ProcessVariablesMap<String, Object> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
        JsonProcessingException {
        ProcessVariablesMap<String, Object> map = new ProcessVariablesMap<>();

        JsonNode node = jp.getCodec().readTree(jp);

        node.fields().forEachRemaining(entry -> {
            String name = entry.getKey();
            JsonNode entryValue = entry.getValue();

            if (!entryValue.isNull()) {
                if (entryValue.get(TYPE) != null && entryValue.get(VALUE) != null) {
                    String type = entryValue.get(TYPE).textValue();
                    String value = entryValue.get(VALUE).asText();

                    Class<?> clazz = ProcessVariablesMapTypeRegistry.forType(type);
                    Object result = conversionService.convert(value, clazz);

                    if (ObjectValue.class.isInstance(result)) {
                        result = ObjectValue.class.cast(result)
                            .getObject();
                    }

                    map.put(name, result);
                } else {
                    Object value = null;
                    try {
                        value = objectMapper.treeToValue(entryValue,
                            Object.class);
                    } catch (JsonProcessingException e) {
                        logger.error("Unexpected Json Processing Exception: ", e);
                    }
                    map.put(name, value);
                }

            } else {
                map.put(name, null);
            }
        });

        return map;
    }
}
