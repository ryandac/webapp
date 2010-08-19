package com.ryan.webapp.service;

import static com.ryan.webapp.model.factory.UserFactory.createUser;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ryan.webapp.dao.UserDao;
import com.ryan.webapp.model.User;

public class UserServiceTest {

	private UserService serviceUnderTest;
	private Mockery mockery;
	private UserDao userDao;
	
	@Before
	public void setUp() {
		mockery = new Mockery();
		userDao = mockery.mock(UserDao.class);
		serviceUnderTest = setUpService();
	}
	
	@After
	public void tearDown() {
		mockery.assertIsSatisfied();
	}

	@Test
	public void addUserWillCallExpectedMethods() {
		final User user = createUser("Ryan", "Da Costa");
		setupExpectations(user);
		serviceUnderTest.addUser(user);
	}

	private UserService setUpService() {
		UserServiceImpl service= new UserServiceImpl();
		service.setUserDao(userDao);
		return service;
	}
	
	private void setupExpectations(final User user) {
		mockery.checking(new Expectations() {{
			one(userDao).addUser(user);
		}});
	}

}
