package com.greatconan.conan.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greatconan.commons.core.base.impl.BaseDAOImpl;
import com.greatconan.conan.model.User;
import com.greatconan.conan.util.ArrayUtils;
import com.greatconan.conan.util.JsonUtils;

public class testdb {
	public static Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);
	public static void main(String[] args) {
		Object[] arg=new Object[]{"111","222",null,"3333","444",null,"55"};
		String sql="select * from user;";
		logger.info("SQL_SENTENCE:" + sql);
		logger.info("SQL_SENTENCE_PARAMES:" + Arrays.toString(ArrayUtils.getObjects(arg)));
		long start=System.currentTimeMillis();
		List a =new ArrayList();
		User b=new User();
		b.setId(2);
		b.setUserName("zyy");
		
		User b2=new User();
		b2.setId(222);
		b2.setUserName("zyy222");
		
		for(int i=0;i<10000000;i++){
			
		}
		a.add(b);
		a.add(b2);
//		a.add("123");
//		a.add("asdd");
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(a));
		//List list = this.jdbcTemplate.queryForList(sql);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
	}
}
