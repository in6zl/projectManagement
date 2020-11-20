/*
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */

package com.pactera.esc.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    static {
        objectMapper = new ObjectMapper();
        // 设置FAIL_ON_EMPTY_BEANS属性，当序列化空对象不要抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModules(new Hibernate5Module(), new AfterburnerModule(), new JavaTimeModule(), new Jdk8Module());
        // 设置FAIL_ON_UNKNOWN_PROPERTIES属性，当JSON字符串中存在Java对象没有的属性，忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DEFAULT_VIEW_INCLUSION,false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Convert Object to JsonString
     *
     * @param jsonObj
     * @return
     */
    public static String jsonObj2Sting(Object jsonObj) {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(jsonObj);
        } catch (IOException e) {
            // TODO: Claude; 6.18;
            e.printStackTrace();
            logger.error("parse json Object[{}] to string failed:", e);
            System.out.printf("parse json Object[{}] to string failed.", jsonObj);
        }

        return jsonString;
    }

    /**
     * Convert JsonString to Simple Object
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T jsonString2SimpleObj(String jsonString, Class<T> cls) {
        T jsonObj = null;
        try {
            jsonObj = objectMapper.readValue(jsonString, cls);
        } catch (IOException e) {
            System.out.printf("pasre json Object[{}] to string failed.", jsonString);
        }

        return jsonObj;
    }

    /**
     * Method that will convert object to the ObjectNode.
     *
     * @param object the source data; if null, will return null.
     * @return the ObjectNode data after converted.
     * @throws Exception
     */
    public static <T> ObjectNode convertObject2ObjectNode(T object)
            throws Exception {
        if (null == object) {
            return null;
        }

        ObjectNode objectNode;

        if (object instanceof String) {
            objectNode = convertJsonStringToObject((String) object,
                    ObjectNode.class);
        } else {
            objectNode = convertValue(object, ObjectNode.class);
        }

        return objectNode;
    }

    /**
     * Method that will convert the json string to destination by the type(cls).
     *
     * @param jsonString the source json string; if null, will return null.
     * @param cls        the destination data type.
     * @return
     * @throws Exception
     */
    public static <T> T convertJsonStringToObject(String jsonString,
                                                  Class<T> cls) throws Exception {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonString, cls);
        } catch (Exception e) {
            // TODO: Claude; 6.18; 不处理就不要catch
            throw new Exception(e);
        }
    }

    public static <T> T jsonToObject(String jsonString, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            logger.debug("jsonToObject error!", e);
        }
        return null;
    }

    /**
     * Method that will convert from given mask into instance of given mask
     * type.
     *
     * @param fromValue
     * @param toValueType
     * @return
     * @throws Exception
     */
    private static <T> T convertValue(Object fromValue, Class<T> toValueType)
            throws Exception {
        try {
            return objectMapper.convertValue(fromValue, toValueType);
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }

    public static <T> T getFiled(String json, String field, Class<T> toValueType) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode node = findJsonNode(jsonNode, field);
            if (null == node) {
                return null;
            }
            String text = node.textValue();
            return convertValue(text, toValueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JsonNode findJsonNode(JsonNode jsonNode, String field) {
        if (null == jsonNode) {
            return null;
        }
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> next = fields.next();
            if (next.getKey().equals(field)) {
                return next.getValue();
            }
            JsonNode node = findJsonNode(next.getValue(), field);
            if (null != node) {
                return node;
            }
        }
        return null;
    }

    public static String toJsonWithView(Object object, Class tClass)
    {
        if(null == object || null == tClass) {
            return "";
        }
        try {
            return objectMapper.writerWithView(tClass).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("toJsonWithView failed", e);
        }
        return "";
    }
}
