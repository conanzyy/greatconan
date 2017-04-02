package com.greatconan.conan.util;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatconan.conan.model.User;
import com.greatconan.conan.service.UserService;

public class PerMinutejobTask {
    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(PerMinutejobTask.class);
    @Autowired
	private  UserService userService;
    /**
     * 业务逻辑处理
     */
    public  void doBiz() {
    	logger.info("------meige------------");
    	User user=new User();
    	  try {
			Method m = userService.getClass().getDeclaredMethod("test", String.class);
			user.setUserName("zyy");
			user.setPassword("12323");
			user.setId(1);
			m.invoke(userService,user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}