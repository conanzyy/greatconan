package com.greatconan.conan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpireJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(ExpireJobTask.class);
    private static final String defineTime="21:00:01";
    /**
     * 业务逻辑处理
     */
    public void doBiz() {
    	 Calendar now = Calendar.getInstance();
//         System.out.println("年：" + now.get(Calendar.YEAR));
//         System.out.println("月：" + (now.get(Calendar.MONTH) + 1));
//         System.out.println("日：" + now.get(Calendar.DAY_OF_MONTH));
//         System.out.println("时：" + now.get(Calendar.HOUR_OF_DAY));
//         System.out.println("分：" + now.get(Calendar.MINUTE));
//         System.out.println("秒：" + now.get(Calendar.SECOND));
    	 	SecendJobTask.doBiz();
    	 	final int second=now.get(Calendar.SECOND);
    	 	final int minute=now.get(Calendar.MINUTE);
    	 	final int hour=now.get(Calendar.HOUR_OF_DAY);
    	 	Date d = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        final String expectTime=sdf.format(d);
    	         
    	 	 new Thread(){
				@Override
				public void run() {
					if(second==0){
		    	 		MinuteJobTask.doBiz();
		    	 	}
				}
    	   }.start();
    	   
    	   new Thread(){
				@Override
				public void run() {
					if(minute==0){
		    	 		HourJobTask.doBiz();
		    	 	}
					}
	   	   }.start();
   	   
		   	new Thread(){
				@Override
				public void run() {
					if(hour==0){
		    	 		DayJobTask.doBiz();
		    	 	}
				}
		   }.start();
   
    	 	
    	 	
       
         
         new Thread(){
				@Override
				public void run() {
					if(expectTime.equals(defineTime)){
			        	 DefinedJobTask.doBiz();
			         }
				}
		   }.start();
		   
         
         
    	
    }
}