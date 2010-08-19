package com.ryan.webapp.dao;

import static com.ryan.webapp.model.factory.UserFactory.createUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.ryan.webapp.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-placeholders.xml",
					    "classpath:applicationContext-dao-test.xml"})
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class})
public class UserDaoIntTest {

	@Autowired(required=true)
	private UserDao userDao;
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Test
	public void willAddAndReloadAUser() {
		User user = createUser("firstName", "surname");
		userDao.addUser(user);

		assertNotSame("saved id should not be 0.", 0L, user.getId());
		assertEquals("firstName", user.getFirstName());
		assertEquals("surname", user.getSurname());
		
		evictAllUsers();
		
		User loadedUser = getUserById(user.getId());
		assertEquals(loadedUser, user);
	}

	private User getUserById(long id) {
		return userDao.getUserById(id);
	}

	private void evictAllUsers() {
		sessionFactory.evict(User.class);
	}

}
