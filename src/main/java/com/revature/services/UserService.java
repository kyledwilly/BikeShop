package com.revature.services;

import java.util.ArrayList;

import com.revature.beans.Bike;
import com.revature.beans.Finance;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;

public interface UserService {
	public Integer addUser(User u);
	public User getUserById(Integer id);
	public User getUserByName(String username);
	public Integer makeOffer(Integer bikeId, Integer userId, Integer amount);
	public Finance acceptOffer(Offer offer);
	public Integer rejectOffer(Offer offer);
	public Offer getOffer(Integer offerId);
	public ArrayList<Offer> listOffers();
	public ArrayList<Payment> listPayments();
	public Integer showRemainingPayments(Integer bikeId);
}
