package com.greatconan.conan.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatconan.conan.service.JobService;
import com.greatconan.conan.service.UserService;

public class SecendJobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(SecendJobTask.class);
    @Autowired
	public  UserService userService;
//    @Autowired
//	private IBaseDAO baseDao;
    @Autowired
	private JobService jobService;
    
    /**
     * 业务逻辑处理
     */
    public   void doBiz() {
    	logger.info("----miao--------------");
//    	User user=new User();
  	  try {
//  		Class<?> forName = Class.forName("com.webex.learning.Test");
//  		Method method = forName.getDeclaredMethod("test", int.class);
//  		method.invoke(forName.newInstance(), 10);  
  	
  		  
  		  //可以使用
//         String className = "com.greatconan.conan.service.impl.UserServiceImp";
//         Class clas =  Class.forName(className);
//  		 Class[] cArg = new Class[1];
//         cArg[0] = User.class;
//		Method m = clas.getDeclaredMethod("test", cArg);//User.class作为第二个参数也可以
//		user.setUserName("zyy");
//		user.setPassword("12323");
//		user.setId(1);
//		m.invoke(clas.newInstance(),user);
  	
  		  
//        String className = "com.greatconan.conan.service.impl.UserServiceImp";
//        Class clas =  UserService.class;
// 		 Class[] cArg = new Class[1];
//        cArg[0] = User.class;
//		Method m = clas.getDeclaredMethod("test", cArg);
//		user.setUserName("zyy");
//		user.setPassword("12323");
//		user.setId(1);
//		m.invoke(userService,user); 		
  		  
  		  
//  		user.setUserName("zyy");
//		user.setPassword("12323");
//		user.setId(1);
//  		userService.test(user);
  		
//  		@SuppressWarnings("rawtypes")
//		List joblist=jobService.getJob();
//  		for(Object job:joblist){
//  			@SuppressWarnings("rawtypes")
//			Map jobmap=(HashMap)job;
//  			String sql=(String) jobmap.get("job_sql");
//  			String args=(String) jobmap.get("job_args");
//  			int job_id=(int) jobmap.get("job_id");
//  			int job_num=(int) jobmap.get("job_num");
//  			Object[] args_obj=JsonUtils.parseJsonStrToBean(args, Object[].class);
//  			String new_sql="update job set job_status = ? , job_num = ? where job_id = ?";
//  			Object[] args_obj_new=new Object[]{"1",job_num,job_id};
//  			//执行前将状态变成执行中
//  			jobService.updateJob(new_sql, args_obj_new);
//  			int result=0;
//  		  try {
//  			result=jobService.updateJob(sql, args_obj);
//  		  } catch (Exception e) {
//			logger.error("Error:doUpdateJob:"+"test"+"{}",e);
//			args_obj_new=new Object[]{"3",job_num+1,job_id};
//			jobService.updateJob(new_sql, args_obj_new);
//  		  }	
//  			//执行后根据状态变成执行成功或失败
//  			if(result!=0){
//  				args_obj_new=new Object[]{"2",job_num+1,job_id};
//  				jobService.updateJob(new_sql, args_obj_new);
//  			}else{
//  				args_obj_new=new Object[]{"3",job_num+1,job_id};
//  				jobService.updateJob(new_sql, args_obj_new);
//  			}
//  			
//  		}
//  		  
  		 String sql="insert into user(uname,pwd) values(?,?)";
  		 String uname= UUID.randomUUID().toString();
  		 String pwd= UUID.randomUUID().toString();
  		 String[] args=new String[]{uname,pwd};
  		 jobService.insertoJob(sql, args);
  		  
  		  
		} catch (Exception e) {
			logger.error("Error:doMenthod:"+"test"+"{}",e);
		}
    	
//    	Spider.create(new Start5())
//        //从"https://github.com/code4craft"开始抓
//        .addUrl("https://github.com/code4craft")
//        //开启5个线程抓取
//        .thread(5)
//        //启动爬虫
//        .run();
  		  
    }
    
    public static void main(String[] args) {
		Object[] obs=new Object[]{"1"};
		System.out.println(Arrays.toString(obs));
//		System.out.println(JsonUtils.formatBeanToJsonStr(obs));
		Object[] obs_new=JsonUtils.parseJsonStrToBean(JsonUtils.formatBeanToJsonStr(obs), Object[].class);
		System.out.println(Arrays.toString(obs_new));
	}
}