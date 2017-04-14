package com.greatconan.conan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatconan.conan.model.User;
import com.greatconan.conan.util.JsonUtils;


@Controller
public class JsonController extends BaseController {
	
	@RequestMapping("/json/getJson")
	public String getJson(@RequestParam ("req")String code,HttpServletRequest request,
		    HttpServletResponse response){
		logger.info("--start-----/json/getJson--");

		//String code=request.getParameter("req");
		User user =new User();
		user.setUserName("testname");
		user.setPassword("pwd");
		
		User user2 =new User();
		user2.setUserName("testname22");
		user2.setPassword("pwd22");
		
		
		if(StringUtils.isNotEmpty(code)){
			user.setId(Integer.parseInt(code));
		}
		
		List list=new ArrayList();
		list.add(user);
		list.add(user2);
		
		Map map=new HashMap();
		if(StringUtils.isNotEmpty(code)){
			map.put("resultCode",1);
		}else{
			map.put("resultCode",0);
		}
		map.put("result",list);
		
		String jsn=JsonUtils.formatBeanToJsonStr(map);

		
//	   ModelAndView mav = new ModelAndView();
//
//        //开始返回
//        MappingJacksonJsonView view = new MappingJacksonJsonView();
//        Map attributes = new HashMap();
//        attributes.put("success", Boolean.TRUE);
//
//        attributes.put("contractNo", "contractNo");
//
//        view.setAttributesMap(attributes);
////        view.s
//	        mav.setView(view);
//	        http://127.0.0.1:8088/conan/json/getJson.json?req=12
		logger.info("--end-----/json/getJson--");
		return ajaxJsonp(response,jsn,null);
	}
	
	
}