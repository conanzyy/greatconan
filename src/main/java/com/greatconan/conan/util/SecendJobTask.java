package com.greatconan.conan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greatconan.conan.test.Start5;

import us.codecraft.webmagic.Spider;

public class SecendJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(SecendJobTask.class);
 
    /**
     * 业务逻辑处理
     */
    public static void doBiz() {
    	logger.info("----miao--------------");
    	Spider.create(new Start5())
        //从"https://github.com/code4craft"开始抓
        .addUrl("https://github.com/code4craft")
        //开启5个线程抓取
        .thread(5)
        //启动爬虫
        .run();
    }
}