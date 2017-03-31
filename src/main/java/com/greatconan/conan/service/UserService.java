package com.greatconan.conan.service;

import java.util.List;

import com.greatconan.conan.model.User;



public interface UserService {
	public boolean register(User user);
	public User loginCheck(User user);
	public List test(User user) ;
}