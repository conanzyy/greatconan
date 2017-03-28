package com.greatconan.conan.test;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class Logtest {
	public static void main(String[] args) {
		org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Start01.class);
		org.apache.log4j.Logger logger = Logger.getLogger(Start01.class);
		LOGGER.debug("slf4--log");
		logger.debug("log4j");
	}
}
