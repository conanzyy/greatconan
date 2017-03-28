/*
 * Copyright (C), 2002-2015, 苏宁易购电子商务有限公司
 * FileName: DateUtils.java
 * Author:   14040897
 * Date:     2015-4-23 上午11:24:49
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.greatconan.conan.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author 14040897
 */
public class SucUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SucUtils.class);

    private static final String DEFAULT_TOWN_NAME = "全区";

    private SucUtils() {
    }

    /**
     * 功能描述: 数组中是否包含关键字信息<br>
     * 〈功能详细描述〉
     * 
     * @param keyValue 包含的关键字
     * @param containArr 遍历的数组
     * @return false ：标识不包含 true：标识包含
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Boolean isContainInArr(String keyValue, String[] containArr) {
        Boolean result = false;
        if (StringUtils.isNotEmpty(keyValue)) {
            for (int i = 0; i < containArr.length; i++) {
                if (containArr[i].equals(keyValue)) {
                    result = true;
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * 功能描述: 将一个String 转换为 int ;<br>
     * 〈功能详细描述〉 1:当入参为空的时候，采用默认值 2：当转换错误的时候，返回0
     * 
     * @param strValue
     * @param defaultVal
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int catInteger(String strValue, int defaultVal) {
        int result;
        if (StringUtils.isEmpty(strValue)) {
            result = defaultVal;
        } else {
            try {
                result = Integer.parseInt(strValue);
            } catch (NumberFormatException e) {
                LOGGER.error("catInteger  error", e);
                result = 0;
            }
        }
        return result;

    }

    /**
     * 
     * 功能描述: 将内容转换为UTF8字符<br>
     * 〈功能详细描述〉
     * 
     * @param context
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String decodeUTF8(String context) {
        String result = null;
        try {
            result = java.net.URLDecoder.decode(context, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decodeUTF8 error=", e);
            result = context;
        }
        return result;
    }

    // 将字符串进行补齐操作
    public static String pad(String num, int n) {
        StringBuilder numBuilder = new StringBuilder();
        if (StringUtils.isEmpty(num)) {
            return null;
        }
        int len = num.length();
        while (len < n) {
            numBuilder.append("0").append(num);
            len++;
        }
        return numBuilder.toString();
    }

    /**
     * 
     * 功能描述: 判断数组中是否包含某个关键的值<br>
     * 〈功能详细描述〉 1:当关键字为null,则直接返回fasle 2:如果存在.则返回true,否则返回false
     * 
     * @param arr
     * @param targetValue
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isContainsInArr(String[] arr, String targetValue) {
        if (StringUtils.isEmpty(targetValue)) {
            return false;
        }
        for (String s : arr) {
            if (s.equals(targetValue)) {
                return true;
            }
        }
        return false;
    }



    /**
     * 隐藏名字 ，显示姓，其余用*代替，用于页面显示。
     * 
     * @param mobile 手机号码
     * @return
     */
    public static String hideName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            StringBuilder sb = new StringBuilder(name.substring(0, 1));
            for (int i = 0; i < name.length() - 1; i++) {
                sb.append("*");
            }
            name = sb.toString();
        }
        return name;
    }




    /**
     * 
     * 功能描述: 将一个数字格式为两位小数的字符串<br>
     * 〈功能详细描述〉
     * 
     * @param str
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getDecimalFormat(String str) {
        String result = null;
        if (StringUtils.isEmpty(str)) {
            return result;
        }
        try {
            double f = Double.parseDouble(str);
            result = String.format("%.2f", f);
        } catch (NumberFormatException e) {
            LOGGER.error("getDecimalFormat Error", e);
        }
        return result;
    }


    /**
     * 掉字符串前面的“0”
     * 
     * @param supplierCode
     * @return
     */
    public static String removeFrontZero(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return str.replaceAll("^(0+)", "");
    }


    /**
     * 将map类型转给javabean格式
     * 
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        final String methodName = "transMap2Bean";
        Object value = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    value = map.get(key);
                    if (value != null) {
                        String va = value.toString();
                        if (!va.startsWith("[")) {
                            // 得到property对应的setter方法
                            Method setter = property.getWriteMethod();
                            setter.invoke(obj, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(methodName + " Error value=" + value + " " + e);
        }
        return;
    }

    /**
     * 获取安全的手机号，隐藏中间六位
     * 
     * @param phoneNum 11位有效手机号码
     * @return
     */
    public static String getSafePhoneNum(String phoneNum) {
        String result = "";

        if (StringUtils.isEmpty(phoneNum)) {
            return result;
        }

        // 手机号码正则
        Pattern p = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");
        if (!p.matcher(phoneNum).matches()) {
            return phoneNum;
        }

        result = phoneNum.substring(0,
                phoneNum.length() - (phoneNum.substring(3)).length())
                + "****" + phoneNum.substring(7);

        return result;
    }

    /**
     * 
     * 功能描述： 获取 字符串格式的 timestamp 时间 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static String getTimestamp() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        return sdfTime.format(date);
    }

    /**
     * 根据对应的字符串格式获得当前时间戳
     * 
     * @param format 字符串格式
     * @return
     */
    public static String getTimestamp(String format) {
        if (StringUtils.isEmpty(format)) {
            return getTimestamp();
            
        } else {
            SimpleDateFormat dateformat = new SimpleDateFormat(format);
            String timeString = dateformat.format(new Date());
            return timeString;
        }
    }

    /**
     * 检验两个集合是否存在交集
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static boolean checkIntersectionOfTwoLists(List<String> list1, List<String> list2) {
        boolean result = false;

        if (CollectionUtils.isNotEmpty(list1) && CollectionUtils.isNotEmpty(list2)) {
            List<String> tempList = new ArrayList<String>();
            tempList.addAll(list1);
            if (tempList.retainAll(list2)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * 把List<String>转换为字符串，元素之间用“;”分隔
     * 
     * @param list
     * @return
     */
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String str : list) {
                if (StringUtils.isNotEmpty(sb.toString())) {
                    sb.append(";");
                }
                sb.append(str);
            }
        }

        return sb.toString();
    }

    /**
     * 将字符串（每个元素之间用“;”分隔）转换为List
     * 
     * @param str
     * @return
     */
    public static List<String> stringToList(String str) {
        List<String> list = new ArrayList<String>();

        if (StringUtils.isNotEmpty(str)) {
            String[] strArray = str.split(";");
            for (String temp : strArray) {
                list.add(temp);
            }
        }

        return list;
    }

    /**
     * 取两个集合的并集
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getUnionOfTwoLists(List<String> list1, List<String> list2) {
        List<String> resultList = new ArrayList<String>();

        if (CollectionUtils.isNotEmpty(list1)) {
            resultList.addAll(list1);
        }

        if (CollectionUtils.isNotEmpty(list2)) {
            for (String str : list2) {
                if (!resultList.contains(str)) {
                    resultList.add(str);
                }
            }
        }

        return resultList;
    }

    /**
     * 功能描述: 给某个字符串添加0<br>
     * 〈功能详细描述〉
     *
     * @param str ：要添加的字符串
     * @param strLength ：最终的字符串长度
     * @param isAppendonFront ：是否添加在字符串的前部
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String appendZeroForNum(String str, int strLength, Boolean isAppendonFront) {
        int strLen = str.length();
        StringBuffer sb;
        while (strLen < strLength) {
            sb = new StringBuffer();
            if (isAppendonFront) {
                // 左(前)补0
                sb.append("0").append(str);
            } else {
                // 右(后)补0
                sb.append(str).append("0");
            }
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉给商品编码加9个0
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String appendZeroCCode(String str, int zeroSize) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroSize; i++) {
            sb.append("0");
        }
        return sb.append(str).toString();
    }



    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param response
     * @param content
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String ajax(HttpServletResponse response, String content) {
        try {
            response.setContentType("text/jsonp;charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
            return null;
        } catch (IOException e) {
            LOGGER.error("IOException:", e);
        }
        return null;
    }
}