package com.ryan.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.ryan.webapp.dao.UserDao;
import com.ryan.webapp.model.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired(required=true)
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Async
	public void addUser(final User user) {
		userDao.addUser(user);
	}
	

}
