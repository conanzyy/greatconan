package com.greatconan.conan.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greatconan.conan.util.comparator.ListComparator;
import com.greatconan.conan.util.comparator.MapComparator;

  
public class BeanUtil {  
	 private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
   public static Map<String, Object> toMap(Object dataObject) throws Exception
   {
     if (dataObject == null) {
       return null;
     }
     if ((dataObject instanceof Map)) {
       return (Map)dataObject;
     }
     JSONObject.toJSON(dataObject);
     JSONObject jsonObject = null;
     try
     {
       jsonObject = (JSONObject)JSONObject.toJSON(dataObject);
     }
     catch (Exception e)
     {
       System.out.println(e.toString());
     }
     if ((jsonObject != null) && (jsonObject.containsKey("success"))) {
       jsonObject.remove("success");
     }
     if (jsonObject.isEmpty()) {
       throw new Exception(dataObject + "转出后的Map是空的");
     }
     Field[] fields = dataObject.getClass().getDeclaredFields();
     try
     {
       for (Field field : fields)
       {
         Object fieldValue = getFieldValue(dataObject, field.getName());
         if ((fieldValue == null) || (field.getName().equals("serialVersionUID"))) {
           jsonObject.remove(field.getName());
         }
       }
     }
     catch (Exception e)
     {
       logger.info("对象转Map时出错:数据对象：{}，异常信息：{}", dataObject.toString(), e);
     }
     return new HashMap(jsonObject);
   }
   
   public static <T> T fromMap(Map<String, Object> dataObject, Class<T> targetClass, String[] excludeFields)
   {
     JSONObject jsonObject = new JSONObject(dataObject);
     for (String field : excludeFields) {
       if (jsonObject.containsKey(field)) {
         jsonObject.remove(field);
       }
     }
     Field[] fields = targetClass.getDeclaredFields();
     try
     {
       for (Field field : fields) {
         if ((field.getType() == List.class) && (jsonObject.get(field.getName()) != null)) {
           if (!"".equals(jsonObject.get(field.getName()).toString().trim()))
           {
             Object value = jsonObject.get(field.getName());
             JSONArray jsonArray = JSONArray.parseArray(String.valueOf(value));
             jsonObject.put(field.getName(), jsonArray);
           }
           else
           {
             jsonObject.put(field.getName(), new JSONArray());
           }
         }
       }
     }
     catch (Exception e)
     {
       logger.info("Map转对象时出错:数据对象：{}，异常信息：{}", dataObject.toString(), e);
     }
     return JSONObject.parseObject(String.valueOf(jsonObject), targetClass);
   }
   
   public static boolean compare(Object beforeItem, Object afterItem, Object... excludeFields) 
   {
     if ((beforeItem == null) && (afterItem == null)) {
       return true;
     }
     if ((beforeItem == null) || (afterItem == null)) {
       return false;
     }
     if ((beforeItem.getClass().isPrimitive()) || ((beforeItem instanceof String)) || ((beforeItem instanceof Long)) || ((beforeItem instanceof Integer)) || ((afterItem instanceof Boolean)) || ((beforeItem instanceof Double)) || ((beforeItem instanceof Date))) {
       return beforeItem.equals(afterItem);
     }
     if (((beforeItem instanceof Map)) && ((afterItem instanceof Map))) {
       try {
		return MapComparator.compare(beforeItem, afterItem, excludeFields, true);
	} catch (Exception e) {
		logger.error("比较异常:异常信息：{"+e+"}",e );
		return false;
	}
     }
     if (((beforeItem instanceof List)) && ((afterItem instanceof List))) {
       try {
		return ListComparator.compare(beforeItem, afterItem, excludeFields);
	} catch (Exception e) {
		logger.error("比较异常:异常信息：{"+e+"}",e );
		return false;
	}
     }
     try
     {
       return MapComparator.compare(beforeItem, afterItem, excludeFields, true);
     }
     catch (Exception e)
     {
       try {
		throw new Exception(e.getCause().toString());
	} catch (Exception e1) {
		logger.error("比较异常:异常信息：{"+e+"}",e );
		return false;
	}
     }
   }
   public static Object getFieldValue(Object sourceObject, String fieldName)
		     throws InvocationTargetException, IllegalAccessException
   {
     Class<? extends Object> sourceClass = sourceObject.getClass();
     
     String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
     if (methodName.indexOf("jacoco") > -1) {
       return null;
     }
     if (!fieldName.equals("serialVersionUID"))
     {
       Method method;
       try
       {
         method = sourceClass.getMethod("get" + methodName, new Class[0]);
       }
       catch (NoSuchMethodException e)
       {
         return null;
       }
       return method.invoke(sourceObject, new Object[0]);
     }
     return null;
   }
    
}