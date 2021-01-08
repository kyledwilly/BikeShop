package com.revature.services;

import com.revature.beans.Bike;
import com.revature.beans.User;

public interface UserService {
	public Integer addUser(User u);
	public User getUserById(Integer id);
	public User getUserByName(String username);
	public Integer makeOffer(Integer bikeId, Integer userId, Integer amount);
}