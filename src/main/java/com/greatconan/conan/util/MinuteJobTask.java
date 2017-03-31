package com.greatconan.conan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinuteJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(MinuteJobTask.class);
 
    /**
     * 业务逻辑处理
     */
    public static void doBiz() {
    	logger.info("------minute------------");
    }
}