package com.greatconan.commons.core.base.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.greatconan.commons.core.base.IBaseDAO;
import com.greatconan.conan.util.JsonUtils;

/**
 * DAO工厂类
 * 可以在此类中增加更多jdbcTemplate中的方法
 * @author lxie
 * **/
@SuppressWarnings("restriction")
@Repository
public class BaseDAOImpl implements IBaseDAO {
	public static Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);
	@Resource
	private JdbcTemplate jdbcTemplate;

	public List queryForList(String sql) {
		logger.info("SQL_SENTENCE:" + sql);
		long start=System.currentTimeMillis();
		List list = this.jdbcTemplate.queryForList(sql);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(list));
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
		return list;
	}

	public List queryForList(String sql, Object[] args) {
		logger.info("SQL_SENTENCE:" + sql);
		logger.info("SQL_SENTENCE_PARAMES:" + Arrays.toString(args));
		long start=System.currentTimeMillis();
		List list = this.jdbcTemplate.queryForList(sql, args);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(list));
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
		return list;
	}

	public Map queryForMap(String sql, Object[] args) {
		logger.info("SQL_SENTENCE:" + sql);
		logger.info("SQL_SENTENCE_PARAMES:" + Arrays.toString(args));
		long start=System.currentTimeMillis();
		Map map = this.jdbcTemplate.queryForMap(sql, args);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(map));
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
		return map;
	}

	public int queryForInt(String sql, Object[] args) {
		logger.info("SQL_SENTENCE:" + sql);
		logger.info("SQL_SENTENCE_PARAMES:" + Arrays.toString(args));
		long start=System.currentTimeMillis();
		int count = this.jdbcTemplate.queryForInt(sql, args);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(count));
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
		return count;
	}

	public int update(String sql, Object[] args) {
		logger.info("SQL_SENTENCE:" + sql);
		logger.info("SQL_SENTENCE_PARAMES:" + Arrays.toString(args));
		long start=System.currentTimeMillis();
		int eff = this.jdbcTemplate.update(sql, args);
		long end=System.currentTimeMillis();
		logger.info("SQL_SENTENCE_RESULT:" + JsonUtils.formatBeanToJsonStr(eff));
		logger.info("SQL_SENTENCE_EXEC_TIME:" + (end-start)+"ms");
		return eff;
	}
}
