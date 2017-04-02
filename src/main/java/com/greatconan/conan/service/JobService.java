package com.greatconan.conan.service;

import java.util.List;

import com.greatconan.conan.model.User;



public interface JobService {
	public List getJob() ;
	public int updateJob(String sql,Object[] qrgs) ;
	public int insertoJob(String sql, Object[] args);
}