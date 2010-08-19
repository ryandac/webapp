package com.ryan.webapp.model.factory;

import com.ryan.webapp.model.User;

public class UserFactory {
	
	public static User createUser(final String firstName, final String surname) {
		final User user = new User();
		user.setFirstName(firstName);
		user.setSurname(surname);
		return user;
	}
}
