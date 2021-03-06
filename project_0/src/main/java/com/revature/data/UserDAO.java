package com.revature.data;

import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.User;

public interface UserDAO extends GenericDAO<User> {
	//create
		public User create(User u) throws Exception;
		
	//read (by id)
		public User read(Integer id);
		
	//read (by username)
		public User read(String username);
		
	//update
		public void update(User u);
		
	//delete
//		public void delete();
		
	//list
		public Set<User> List();
		
		public Integer makeOffer(Offer offer);
}
