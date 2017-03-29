package com.greatconan.conan.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;

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
}
