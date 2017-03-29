package com.greatconan.conan.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.greatconan.conan.model.User;
import com.greatconan.conan.util.BeanUtil;
import com.greatconan.conan.util.JsonUtils;



public class TestUtils {
public static void main(String[] args) {
	String actualResult="upload.ftl";
	User user =new User();
	User user2 =new User();
	
	user.setId(1);
	user2.setId(1);
	
	List list=new ArrayList();
	list.add("qwe");
	list.add("ewq");
	list.add(user);
	
	List list2=new ArrayList();
	list2.add("ewq");
	list2.add("qwe");
	list2.add(user2);
	
	Map map=new HashMap();
	map.put("a","b");
	map.put("user",user);
	map.put("c","d");
	
	Map map2=new HashMap();
	map2.put("c","d");
	map2.put("a","b");
	map2.put("user",user);
	
	
	boolean stringflag=BeanUtil.compare("upload.ftl", actualResult);
	boolean listflag=BeanUtil.compare(list, list2);//顺序不一样可以 转json
	boolean mapflag=BeanUtil.compare(map, map2);//顺序不一样可以 直接对象 没有转json
	
	System.out.println(stringflag);
	System.out.println(listflag);
	System.out.println(mapflag);
	JsonUtils.formatBeanToJsonStr(user);
	JsonUtils.parseJsonStrToBean("", User.class);
	
	StringUtils.isEmpty("");
	CollectionUtils.isEmpty(list);
	MapUtils.isEmpty(map2);
	
	
	
}
}
