package com.greatconan.conan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatconan.conan.dao.UserDao;
import com.greatconan.conan.model.User;
import com.greatconan.conan.service.UserService;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User loginCheck(User user) {
		User u  = userDao.findUserByUserName(user.getUserName());
		System.out.println("id="+u.getId()+",  userName="+u.getUserName()+", password="+u.getPassword());
		if(user.getPassword().equals(u.getPassword())){
			return u;
		}
		else{
			return null;
		}
	}

	public boolean register(User user) {
		User u =  userDao.findUserByUserName(user.getUserName());
		if(u.getId()==0){
			userDao.register(user);
			return true;
		}
		else{
			System.out.println("id="+u.getId()+",  userName="+u.getUserName()+", password="+u.getPassword());
			return false;
		}
	}
	public List test(User user) {
		List u  = userDao.test();
//		System.out.println("id="+u.getId()+",  userName="+u.getUserName()+", password="+u.getPassword());
//		if(user.getPassword().equals(u.getPassword())){
//			return u;
//		}
//		else{
//			return null;
//		}
		return u;
	}

}