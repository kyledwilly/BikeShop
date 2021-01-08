package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.beans.Bike;
import com.revature.beans.BikeStatus;
import com.revature.beans.Finance;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	
	public UserServiceImpl() {
		UserDAOFactory userDAOFactory = new UserDAOFactory();
		userDAO = userDAOFactory.getUserDAOFactory();
	}
	
	@Override
	public Integer addUser(User u) {
		try{
			User newUser = userDAO.create(u);
			
			return newUser.getId();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		return userDAO.read(id);
	}

	@Override
	public User getUserByName(String username) {
		return userDAO.read(username);
	}

	@Override
	public Integer makeOffer(Integer bikeId, Integer userId, Integer amount) {
		Offer offer = new Offer();
		offer.setAmount(amount);
		offer.setBikeId(bikeId);
		offer.setCustomerId(userId);
		
		return userDAO.makeOffer(offer);
	}

	@Override
	public Finance acceptOffer(Offer offer) {
		Finance finance = new Finance();
		finance = userDAO.acceptOffer(offer);
		return finance;
	}

	@Override
	public Integer rejectOffer(Offer offer) {
		return userDAO.rejectOffer(offer);
	}

	@Override
	public Offer getOffer(Integer offerId) {
		return userDAO.getOfferById(offerId);
	}
	public ArrayList<Offer> listOffers(){
		return userDAO.listOffers();
	}

	@Override
	public ArrayList<Payment> listPayments() {	
		return userDAO.listPayments();
	}

	@Override
	public Integer showRemainingPayments(Integer bikeId) {

		return userDAO.showRemainingPayments(bikeId);
		
	}

}