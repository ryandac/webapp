package com.ryan.webapp.dao;

import com.ryan.webapp.model.User;

public interface UserDao {

	public void addUser(User user);
	
	public User getUserById(long id);
	
}
