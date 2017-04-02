package com.greatconan.conan.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatconan.conan.service.JobService;

public class MinuteJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(MinuteJobTask.class);
    @Autowired
	private JobService jobService;
    /**
     * 业务逻辑处理
     */
    public  void doBiz() {
    	logger.info("------minute------------");
  	  try {
  		@SuppressWarnings("rawtypes")
		List joblist=jobService.getJob();
  		for(Object job:joblist){
  			@SuppressWarnings("rawtypes")
			Map jobmap=(HashMap)job;
  			String sql=(String) jobmap.get("job_sql");
  			String args=(String) jobmap.get("job_args");
  			int job_id=(int) jobmap.get("job_id");
  			int job_num=(int) jobmap.get("job_num");
  			Object[] args_obj=JsonUtils.parseJsonStrToBean(args, Object[].class);
  			String new_sql="update job set job_status = ? , job_num = ? where job_id = ?";
  			Object[] args_obj_new=new Object[]{"1",job_num,job_id};
  			//执行前将状态变成执行中
  			jobService.updateJob(new_sql, args_obj_new);
  			int result=0;
  		  try {
  			result=jobService.updateJob(sql, args_obj);
  		  } catch (Exception e) {
			logger.error("Error:doUpdateJob:"+sql+"{}",e);
			args_obj_new=new Object[]{"3",job_num+1,job_id};
			jobService.updateJob(new_sql, args_obj_new);
  		  }	
  			//执行后根据状态变成执行成功或失败
  			if(result!=0){
  				args_obj_new=new Object[]{"2",job_num+1,job_id};
  				jobService.updateJob(new_sql, args_obj_new);
  			}else{
  				args_obj_new=new Object[]{"3",job_num+1,job_id};
  				jobService.updateJob(new_sql, args_obj_new);
  			}
  			
  		}
  		  
		} catch (Exception e) {
			logger.error("Error:doMenthod:"+"MinuteJobTask"+"{}",e);
		}
    	
    }
}