package com.revature.data;

import java.util.ArrayList;
import java.util.Set;

import com.revature.beans.Finance;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.User;

public interface UserDAO extends GenericDAO<User> {

		public User create(User u) throws Exception;

		public User read(Integer id);

		public User read(String username);

		public void update(User u);
		public Set<User> List();
		
		public Integer makeOffer(Offer offer);
		public Finance acceptOffer(Offer offer);
		public Integer rejectOffer(Offer offer);
		public Offer getOfferById(Integer offerId);
		public ArrayList<Offer> listOffers();
		public ArrayList<Payment> listPayments();
		public Integer showRemainingPayments(Integer bikeId);
}
