package com.greatconan.conan.dao;

import java.util.List;

import com.greatconan.conan.model.User;


public interface UserDao {
	public void register(User user);
	public User findUserByUserName(final String userName);
	public List test(User user);
}