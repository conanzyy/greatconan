package com.greatconan.conan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.greatconan.commons.core.base.IBaseDAO;
import com.greatconan.conan.dao.UserDao;
import com.greatconan.conan.model.User;


@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IBaseDAO baseDao;
	
	public void register(User user) {
		String sqlStr = "insert into user(uname,pwd) values(?,?)";
		Object[] params = new Object[]{user.getUserName(),user.getPassword()};
		jdbcTemplate.update(sqlStr, params);
	}

	public User findUserByUserName(String userName) {
		String sqlStr = "select id,uname,pwd from user where uname=?";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("uname"));
				user.setPassword(rs.getString("pwd"));
			}
		});
		return user;
	}
	
	
	public List test(User user){
		String sql = "select id,uname,pwd from user where 1=1 ";
		if(user.getId()!=0){
			sql+="and id=? ";
		}
		if(user.getUserName()!=null){
			sql+="and uname=? ";
		}
		Object[] args=new Object[]{user.getId(),user.getUserName()};
		List list = baseDao.queryForList(sql,args);
		return list;
	}

}