package com.greatconan.conan.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatconan.conan.model.User;
import com.greatconan.conan.service.UserService;
import com.greatconan.conan.util.JsonUtils;


@Controller
public class JsonController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/json/getJson")
	public String getJson(HttpServletRequest request,
		    HttpServletResponse response){
		logger.info("--start-----/json/getJson--");

		User user =new User();
		user.setUserName("testname");
		user.setPassword("pwd");
		
		List list=new ArrayList();
		list.add("qwe");
		list.add("ewq");
		list.add(user);
		
		String jsn=JsonUtils.formatBeanToJsonStr(list);

		
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
//	        
		logger.info("--end-----/json/getJson--");
		return ajax(response,jsn);
	}
	
	
}