package com.greatconan.conan.util.comparator;

import java.util.List;
import java.util.Map;

import com.greatconan.conan.util.BeanUtil;

public class MapComparator
  extends Comparator
{
  public static boolean compare(Object beforeItem, Object afterItem, Object[] excludeFields, boolean isPrintDiff) throws Exception
  {
    Map beforeMap = BeanUtil.toMap(beforeItem);
    Map afterMap = BeanUtil.toMap(afterItem);
    for (Object field : excludeFields)
    {
      if (beforeMap.containsKey(field)) {
        beforeMap.remove(field);
      }
      if (afterMap.containsKey(field)) {
        afterMap.remove(field);
      }
    }
    return compare(beforeMap, afterMap, isPrintDiff);
  }
  
  public static boolean compare(Map beforeItem, Map afterItem, boolean isPrintDiff) throws Exception
  {
    if ((beforeItem == null) && (afterItem == null)) {
      return true;
    }
    if ((beforeItem == null) || (afterItem == null))
    {
      printDiff("两个Map中，有一个为null", beforeItem, afterItem, isPrintDiff);
      return false;
    }
    if (beforeItem.size() != afterItem.size())
    {
      printDiff("两个Map的Size不同", beforeItem, afterItem, isPrintDiff);
      return false;
    }
    for (Object key : beforeItem.keySet())
    {
      if (!afterItem.containsKey(key))
      {
        printDiff("发现了缺失的Key", key, null, isPrintDiff);
        return false;
      }
      try {
		if (!isEquals(key, beforeItem.get(key), afterItem.get(key), isPrintDiff)
				.booleanValue()) {
			return false;
		}
		} catch (Exception e) {
			continue;
		}
    }
    return true;
  }
  
  private static Boolean isEquals(Object key, Object beforeValue, Object afterValue, boolean isPrintDiff) throws Exception
  {
    if ((beforeValue == null) && (afterValue == null)) {
      return Boolean.valueOf(true);
    }
    if ((beforeValue == null) || (afterValue == null))
    {
      printDiff("Key{" + key + "}两个值中，有一个为null", beforeValue, afterValue, isPrintDiff);
      return Boolean.valueOf(false);
    }
    if (isMapCompare(beforeValue, afterValue).booleanValue()) {
      return Boolean.valueOf(compare((Map)beforeValue, (Map)afterValue, isPrintDiff));
    }
    if (isListCompare(beforeValue, afterValue).booleanValue()) {
      return ListComparator.compare((List)beforeValue, (List)afterValue);
    }
    if (beforeValue == null)
    {
      System.out.println("======================================error");
      return Boolean.valueOf(false);
    }
    if (!beforeValue.equals(afterValue))
    {
      printDiff("Key{" + key + "}在两个对象中的值或类型不相等", beforeValue, afterValue, isPrintDiff);
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }
  
  private static Boolean isMapCompare(Object beforeValue, Object afterValue)
  {
    return Boolean.valueOf(((beforeValue instanceof Map)) && ((afterValue instanceof Map)));
  }
  
  private static Boolean isListCompare(Object beforeValue, Object afterValue)
  {
    return Boolean.valueOf(((beforeValue instanceof List)) && ((afterValue instanceof List)));
  }
}


