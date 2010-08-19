package com.ryan.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ryan.webapp.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}
	
	public User getUserById(long id) {
		return getHibernateTemplate().get(User.class, id);
	}
	
	public List<User> all() {
    	return new ArrayList<User>( getHibernateTemplate().loadAll(User.class) );
    }

}
