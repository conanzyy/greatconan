package com.greatconan.conan.test;


import java.io.File;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.greatconan.conan.util.FtpUtil;

public class Start01 implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
    	Logger logger = Logger.getLogger(Start01.class);
    	logger.debug("---test-start---");
//    	FtpUtil ftpUtil =new FtpUtil();
//    	FtpUseBean ftpUseBean =new FtpUseBean();
//    	ftpUseBean.setUserName("conanzyy");
//    	ftpUseBean.setPassword("123456");
//    	ftpUseBean.setFtpPath("");
//    	ftpUseBean.setPort(21);
//    	ftpUseBean.setHost("127.0.0.1");
//    	
//    	ftpUtil.setFtpUseBean(ftpUseBean);
//    	ftpUtil.ftpLogin();
    	
/*    	FtpUtils ftpUtils =new FtpUtils();
    	ftpUtils.connect("", "127.0.0.1", 21, "conanzyy", "123456");    
        File file = new File("f:\\todo"); 
//        InputStream is=new FileInputStream(file);
        ftpUtils.upload(file);*/
    	
    	
    	  FtpUtil ftpUtil=new FtpUtil();  
    	    ftpUtil.connectServer("127.0.0.1", FTPClient.DEFAULT_PORT, "conanzyy", "123456", "/log");  
    	    //获得ftp服务器上目录名称为DF4下的所有文件名称  
    	    List<String> list=ftpUtil.getFileList("/Git");  
    	    System.out.println("文件名称列表为:"+list);  
    	    //上传本地D盘文件aaa.txt到服务器，服务器上名称为bbb.txt  
    	    ftpUtil.uploadFile("f:" + File.separator + "test.txt", "eee.txt");  
    	    //从服务器上下载文件bbb.txt到本地d盘名称为ccc.txt  
    	    //ftpUtil.download("eee.txt", "d:" + File.separator + "fff.txt");  
    	    //删除ftp服务器上文件:bbb.txt  
    	    //ftpUtil.deleteFile("bbb.txt");  
    	
//        ftpUtils.uploadFile("127.0.0.1", 21, "conanzyy", "123456","","1.txt",is);
        
//        Spider.create(new Start01()).addUrl("https://github.com/code4craft").thread(5).run();
    	logger.debug("---test-end---");
    }
}