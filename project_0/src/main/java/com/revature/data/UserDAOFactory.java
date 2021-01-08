package com.revature.data;

public class UserDAOFactory {
	public UserDAO getUserDAOFactory() {
		return new UserPostgres();
	}
}
