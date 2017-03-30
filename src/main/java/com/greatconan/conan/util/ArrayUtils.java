/*
 * FileName: JsonUtils.java
 * Author:   16031081
 * Date:     2016年5月21日 上午9:52:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.greatconan.conan.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 〈数组去空处理〉<br>
 * 〈功能详细描述〉
 * 
 * @author 13073388
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ArrayUtils {

    private ArrayUtils() {

    }

    /**
     * 功能描述: <br>
     * 数组转成非空数组
     * 
     */
    public static Object[] getObjects(Object[] args) {
    	List<Object> list = new ArrayList<Object>();  
    	for(int i=0;i<args.length;i++){
    		if(null!=args[i]){
    			list.add(args[i]); 
    		}
    	}
    	return list.toArray();
    }

    /**
     * 功能描述: <br>
     * 获取数组中非空变量数目
     * 
     */
    public static int getObjectNum(Object[] args) {
    	return getObjects(args).length;
    }


}
