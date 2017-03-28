/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: JsonConvert.java
 * Author:   Administrator
 * Date:     2014-10-14 下午8:34:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.greatconan.conan.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * json转换类
 */
public class JsonConvert {

    private static final ObjectMapper MPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConvert.class);
    
    private JsonConvert() {
    }

    /**
     * json 转换为 对象
     * 
     * @param json json串
     * @param cl 类型
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> T jsonToObject(String json, Class<T> cl) {
        LOGGER.debug("Enter method jsonToObject");

        LOGGER.debug("param=" + json);
        try {
            return MPPER.readValue(json.getBytes("UTF-8"), cl);
        }  catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }
        LOGGER.debug("Exit method jsonToObject");
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String json, TypeReference<T> cl) {
        LOGGER.debug("Enter method jsonToObject1");
        LOGGER.debug("param=" + json);
        try {
            return (T) MPPER.readValue(json, cl);
        } catch (Exception e) {
            LOGGER.error("jsonToObject1 occur Exception", e);
        }
        LOGGER.debug("Exit method jsonToObject");
        return null;
    }

    /**
     * List转换为JSON格式的String
     * 
     * @param t 集合
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> List<T> setToList(Set<String> msgList, Class<?> t) {

        // 空校验
        if (null == msgList) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<T>();
        // 转换为对象
        for (String msg : msgList) {
            list.add((T) JsonConvert.jsonToObject(msg, t));
        }
        return list;
    }

    /**
     * List转换为JSON格式的String
     * 
     * @param t 集合
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> String[] listoString(List<T> t) {

        String[] temp = new String[t.size()];

        for (int i = 0; i < t.size(); i++) {
            temp[i] = objectoJson(t.get(i));
        }

        return temp;
    }

    /**
     * 对象转换为json
     * 
     * @param obj 需要转换对象
     * @return 转换后的json串
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String objectoJson(Object obj) {
        LOGGER.debug("Enter method objectoJson");
        try {
            return MPPER.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            LOGGER.error("JsonGenerationException", e);
        } catch (JsonMappingException e) {
            LOGGER.error("JsonMappingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.debug("Exit method objectoJson");
        return null;
    }
}
