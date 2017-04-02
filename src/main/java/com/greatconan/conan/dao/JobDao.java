package com.greatconan.conan.dao;

import java.util.List;

import com.greatconan.conan.model.User;


public interface JobDao {
	public List getJob();
	public int updateJob(String sql,Object[] args);
	public int insertJob(String sql, Object[] args);
}