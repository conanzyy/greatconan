 package com.greatconan.conan.util.comparator;
 
 import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.greatconan.conan.util.BeanUtil;
 
 public class ListComparator
   extends Comparator
 {
   public static boolean compare(Object beforeItem, Object afterItem, Object[] excludeFields) throws Exception
   {
     JSONArray beforeArray = (JSONArray)JSONArray.toJSON(beforeItem);
     
     JSONArray afterArray = (JSONArray)JSONArray.toJSON(afterItem);
     if (beforeArray.size() != afterArray.size()) {
       return false;
     }
     for (Object field : excludeFields) {
       for (int i = 0; i < beforeArray.size(); i++) {
         if ((beforeArray.getJSONObject(i).containsKey(field)) || (afterArray.getJSONObject(i).containsKey(field)))
         {
           beforeArray.getJSONObject(i).remove(field);
           afterArray.getJSONObject(i).remove(field);
         }
       }
     }
     return compare(beforeArray, afterArray).booleanValue();
   }
   
   public static Boolean compare(List beforeItem, List afterItem) throws Exception
   {
     if ((beforeItem == null) || (afterItem == null))
     {
       printDiff("两个List中，有一个为null", beforeItem, afterItem);
       return Boolean.valueOf(false);
     }
     if (beforeItem.size() != afterItem.size())
     {
       printDiff("两个List的Size不同", beforeItem, afterItem);
       return Boolean.valueOf(false);
     }
     for (Object item : beforeItem) {
       if (!isContain(item, afterItem).booleanValue())
       {
         printDiff("一个对象没有在两个List中同时出现，具体信息请继续查看下一条输出。异常对象->" + item, beforeItem, afterItem);
         return Boolean.valueOf(false);
       }
     }
     for (Object item : afterItem) {
       if (!isContain(item, beforeItem).booleanValue())
       {
         printDiff("一个对象没有在两个List中同时出现，具体信息请继续查看上一条输出。异常对象->" + item, afterItem, beforeItem);
         return Boolean.valueOf(false);
       }
     }
     return Boolean.valueOf(true);
   }
   
   private static Boolean isContain(Object item, List targetList) throws Exception
   {
     if ((item instanceof Map))
     {
       for (Object targetItem : targetList) {
         if ((targetItem instanceof Map))
         {
           boolean result = MapComparator.compare((Map)item, (Map)targetItem, false);
           if (!result) {
             break;
           }
           return Boolean.valueOf(true);
         }
       }
		findOutWhatsDiffInDetail((Map)item, targetList);
	
     }
     if ((item instanceof List)) {
       for (Object targetItem : targetList) {
         if ((targetItem instanceof List))
         {
           boolean result = compare((List)item, (List)targetItem).booleanValue();
           if (result) {
             return Boolean.valueOf(true);
           }
         }
       }
     }
     for (Object targetItem : targetList) {
       if (targetItem.equals(item)) {
         return Boolean.valueOf(true);
       }
     }
     return Boolean.valueOf(false);
   }
   
   private static void findOutWhatsDiffInDetail(Map item, List targetList) throws Exception 
   {
     for (Object key : item.keySet())
     {
       boolean isHasKey = false;
       boolean isHasValue = false;
       for (Object targetItem : targetList) {
         if ((targetItem instanceof Map))
         {
           Map targetMap = (Map)targetItem;
           if (targetMap.containsKey(key)) {
             isHasKey = true;
           }
           if (BeanUtil.compare(item.get(key), targetMap.get(key), new Object[0])) {
             isHasValue = true;
           }
         }
       }
       if (!isHasKey) {
         printDiff("没有找到Key:" + key, item, targetList);
       }
       if (!isHasValue) {
         printDiff("目标集合元素中没有匹配到的key：" + key + "->" + item.get(key), item, targetList);
       }
     }
   }
 }


