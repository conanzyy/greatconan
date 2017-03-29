package com.greatconan.conan.controller;


import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;

public class BaseController {
	public Logger LOGGER = Logger.getLogger(BaseController.class);
	DailyRollingFileAppender a =new DailyRollingFileAppender();
}
