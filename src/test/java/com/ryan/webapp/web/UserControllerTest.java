package com.ryan.webapp.web;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.ryan.webapp.model.User;
import com.ryan.webapp.model.factory.UserFactory;
import com.ryan.webapp.service.UserService;

public class UserControllerTest {

	UserController underTest;
	private Mockery mockery;
	private UserService userService;

	@Before
	public void setUp() {
		mockery = new Mockery();
		userService = mockery.mock(UserService.class);
		underTest = new UserController();
		underTest.setUserService(userService);
	}

	@After
	public void tearDown() {
		mockery.assertIsSatisfied();
	}

	@Test
	public void testInvokesAddUserCorrectly() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/user.htm");
		request.setMethod("POST");
		request.addParameter("firstName", "Ryan");
		request.addParameter("surname", "Da Costa");

		final User user = UserFactory.createUser("Ryan", "Da Costa");

		mockery.checking(new Expectations() {{
			one(userService).addUser(user);
		}});

		ModelAndView modelAndView = new AnnotationMethodHandlerAdapter()
				.handle(request, new MockHttpServletResponse(), underTest);

		assertEquals("redirect:result", modelAndView.getViewName());
	}
	
	@Test
	public void testGetMethodGoesToForm() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/user.htm");
		request.setMethod("GET");
		
		ModelAndView modelAndView = new AnnotationMethodHandlerAdapter()
			.handle(request, new MockHttpServletResponse(), underTest);
		
		assertEquals("form", modelAndView.getViewName());
	}

}
