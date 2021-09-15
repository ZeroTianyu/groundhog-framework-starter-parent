package com.groundhog.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.groundhog.base.excption.GroundhogException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guotianyu
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        objectMapper.registerModule(timeModule);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }


    /**
     * 对象转json
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new GroundhogException();
        }

        return result;
    }


    /**
     * json转对象
     *
     * @param json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> t) throws JsonProcessingException {
        return objectMapper.readValue(json, t);
    }

    /**
     * jsonArray 转 list
     *
     * @param json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> t) throws JsonProcessingException {

        return objectMapper.readValue(json, ArrayList.class);
    }
}