package com.greatconan.conan.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	public static Logger logger = Logger.getLogger(BaseController.class);
	
	DailyRollingFileAppender a =new DailyRollingFileAppender();
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
	        	logger.error("IOException:", e);
	        }
	        return null;
	    }
	 /**
	     * 处理Controller中未捕获的运行时异常
	     * 
	     * @param exception
	     * @param request
	     */
	    @ExceptionHandler(Exception.class)
	    public ModelAndView runtimeExceptionHandler(Exception exception) {
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("error");
	        mav.addObject("exception", exception);
//	        logger.logException(exception);
	        logger.error("异常信息: ", exception);
	        return mav;

	    }
	    public String ajaxJsonp(HttpServletResponse response,String jsonString,String callback) {
	        if (StringUtils.isEmpty(callback)) {
	            return ajax(response, jsonString);
	        }
	        else {
	             return ajax(response, callback + "(" +jsonString + ");");
	             
	        }
	    }
}

