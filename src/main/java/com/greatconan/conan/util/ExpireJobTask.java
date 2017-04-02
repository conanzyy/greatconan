package com.greatconan.conan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpireJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(ExpireJobTask.class);
    private static final String defineTime="03:00:00";
    private static final int perMinute=30;
    @Autowired
    private SecendJobTask secendJobTask;
    @Autowired
    private MinuteJobTask minuteJobTask;
    @Autowired
    private HourJobTask hourJobTask;
    @Autowired
    private DayJobTask dayJobTask;
    @Autowired
    private DefinedJobTask definedJobTask;
    @Autowired
    private PerMinutejobTask perMinutejobTask;
    

	public SecendJobTask getSecendJobTask() {
		return secendJobTask;
	}


	public void setSecendJobTask(SecendJobTask secendJobTask) {
		this.secendJobTask = secendJobTask;
	}


	public MinuteJobTask getMinuteJobTask() {
		return minuteJobTask;
	}


	public void setMinuteJobTask(MinuteJobTask minuteJobTask) {
		this.minuteJobTask = minuteJobTask;
	}


	public HourJobTask getHourJobTask() {
		return hourJobTask;
	}


	public void setHourJobTask(HourJobTask hourJobTask) {
		this.hourJobTask = hourJobTask;
	}


	public DayJobTask getDayJobTask() {
		return dayJobTask;
	}


	public void setDayJobTask(DayJobTask dayJobTask) {
		this.dayJobTask = dayJobTask;
	}


	public DefinedJobTask getDefinedJobTask() {
		return definedJobTask;
	}


	public void setDefinedJobTask(DefinedJobTask definedJobTask) {
		this.definedJobTask = definedJobTask;
	}


	public PerMinutejobTask getPerMinutejobTask() {
		return perMinutejobTask;
	}


	public void setPerMinutejobTask(PerMinutejobTask perMinutejobTask) {
		this.perMinutejobTask = perMinutejobTask;
	}


	public static int getPerminute() {
		return perMinute;
	}


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
    	 
	    	 new Thread(){
					@Override
					public void run() {
					//	secendJobTask.doBiz();
					}
	 	   }.start();
    	 	
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
					//	minuteJobTask.doBiz();
		    	 	}
				}
    	   }.start();
    	   
    	   new Thread(){
				@Override
				public void run() {
					if(minute==0 && second==0){
						hourJobTask.doBiz();
		    	 	}
					}
	   	   }.start();
   	   
		   	new Thread(){
				@Override
				public void run() {
					if(hour==0 && minute==0 && second==0 ){
						dayJobTask.doBiz();
		    	 	}
				}
		   }.start();
   
    	 	
    	 	
       
         
         new Thread(){
				@Override
				public void run() {
					if(expectTime.equals(defineTime)){
			        	 definedJobTask.doBiz();
			         }
				}
		   }.start();
		   
		   new Thread(){
				@Override
				public void run() {
					if(minute%perMinute==0 && second==0){
						perMinutejobTask.doBiz();
			         }
				}
		   }.start();
         
    	
    }
}