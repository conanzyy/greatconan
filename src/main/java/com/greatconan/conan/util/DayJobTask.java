package com.greatconan.conan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(DayJobTask.class);
 
    /**
     * 业务逻辑处理
     */
    public static void doBiz() {
    	logger.info("-----day-------------");
    }
}