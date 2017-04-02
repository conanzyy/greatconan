package com.greatconan.conan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatconan.conan.dao.JobDao;
import com.greatconan.conan.dao.UserDao;
import com.greatconan.conan.model.User;
import com.greatconan.conan.service.JobService;
import com.greatconan.conan.service.UserService;
import com.greatconan.conan.util.SecendJobTask;


@Service
public class JobServiceImp implements JobService {
	
	 private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private JobDao jobDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List getJob() {
		return jobDao.getJob();
	}

	@Override
	public int updateJob(String sql, Object[] args) {
		return jobDao.updateJob(sql, args);
	}
	@Override
	public int insertoJob(String sql, Object[] args) {
		return jobDao.insertJob(sql, args);
	}

}