package com.greatconan.conan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatconan.commons.core.base.IBaseDAO;
import com.greatconan.conan.dao.JobDao;
import com.greatconan.conan.util.JsonUtils;


@Repository
public class JobDaoImpl implements JobDao {
	
	@Autowired
	private IBaseDAO baseDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getJob() {
		//job_statu: 0： 待执行，1： 执行中，2 ：执行成功 3： 执行失败
		String sql = "select job_id,job_sql,job_args,job_status,job_num from job where job_num<30 and (job_status=0 or job_status=3) ";
		List list = baseDao.queryForList(sql);
		return list;
	}

	@Override
	public int updateJob(String sql, Object[] args) {
		return baseDao.update(sql, args);
	}

	@Override
	public int insertJob(String sql, Object[] args) {
		String exec_sql = "insert into job(job_sql, job_args,job_status,job_num) VALUES (?, ?,0,0) ";
		Object[] exec_objects=new Object[]{sql,JsonUtils.formatBeanToJsonStr(args)};
		return baseDao.update(exec_sql, exec_objects);
	}
}