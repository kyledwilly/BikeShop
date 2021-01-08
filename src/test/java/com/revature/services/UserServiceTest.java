package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.data.UserDAO;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

//	@Mock
//	static UserDAO userDao;
//	
//	@InjectMocks
//	static UserServiceImpl userServ;
//	
//	static Integer userSequenceMock = 1;


//	@Test
//	public void testMockAddUser() throws Exception {
//		User user = new User();
//		Role role = new Role();
//		user.setId(1);
//		user.setName("MockitoTestName");
//		user.setPassword("MockitoTestPassword");
//		user.setRole(role);
//		
//		User user2 = new User();
//		user2.setId(2);
//		user2.setName("MockitoTestName2");
//		user2.setPassword("MockitoTestPassword2");
//		user2.setRole(role);
//
//		when(userDao.create(user)).thenReturn(user2);
//		assertNotEquals(user.getId().intValue(), userServ.addUser(user).intValue());
//		
//		verify(userDao).create(user);
//	}
	
	@Test
	public void testNewUser() {
		User user = new User();
		user.setName("Kyall");
		assertEquals("Kyall", user.getName());
	}
	
	static private Integer newUserId = null;
	static private User newUser = null;
	UserService userServ = new UserServiceImpl();
//public Integer addUser(User u);
	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("Alex");
		user.setPassword("pass");
		Role role = new Role();
		user.setRole(role);
		newUserId = userServ.addUser(user);
		newUser = user;
		assertTrue(newUserId > 0);	
	}
//public User getUserById(Integer id);
	@Test
	public void testgetUserById() {
		User user = userServ.getUserById(newUserId);
		assertTrue(user.getPassword().equalsIgnoreCase(newUser.getPassword()));
		assertTrue(user.getRole().getId() == newUser.getRole().getId());
	}
	
//public User getUserByName(String username);	
	@Test
	public void testgetUserByName() {
		User user = userServ.getUserByName("Max");
		assertTrue(user.getPassword().equalsIgnoreCase(newUser.getPassword()));
		assertTrue(user.getRole().getId() == newUser.getRole().getId());
	}
	

	private static Integer bikeId = 18;
	private static Integer offerId;
	private static Offer offer;
	
	@Test
	public void testAllOfferServices() {
//public Integer makeOffer(Integer bikeId, Integer userId, Integer amount);	
		offerId = userServ.makeOffer(bikeId, 4, 1200);
		assertTrue(offerId > 0);
		
//public Offer getOffer(Integer offerId);
		offer = userServ.getOffer(offerId);
		assertTrue(offer.getAmount() == 1200);
		assertTrue(offer.getBikeId() == bikeId);
		assertTrue(offer.getId() == offerId);
		assertTrue(offer.getCustomerId() == 4);
		
//public ArrayList<Offer> listOffers();
		ArrayList<Offer> offers = userServ.listOffers();
		Integer offersSize = offers.size();
		assertTrue(offersSize > 0);
		
//public Integer rejectOffer(Offer offer);
		userServ.rejectOffer(offer);
		offers = userServ.listOffers();
		assertTrue(offers.size() < offersSize);
		
		offerId = null;
		offer = null;
		
		offerId = userServ.makeOffer(bikeId, 4, 1200);
		offer = userServ.getOffer(offerId);
		assertTrue(offer != null);
		assertTrue(offerId != null);

		offers = userServ.listOffers();
		offersSize = offers.size();
		assertTrue(offersSize > 0);
	
		offerId = userServ.makeOffer(bikeId, 4, 1200);
		assertTrue(offerId > 0);
		offer = userServ.getOffer(offerId);
		
//public Finance acceptOffer(Offer offer);
		userServ.acceptOffer(offer);
		offers = userServ.listOffers();
		assertTrue(offers.size() < offersSize);		
	}	
	
	@Test
	public void testAllPaymentFunctions() {
//public ArrayList<Payment> listPayments();
		ArrayList<Payment> payments = userServ.listPayments();
		assertTrue(payments.size() > 0);

//public Integer showRemainingPayments(Integer bikeId);
		Integer remainingPayments = userServ.showRemainingPayments(18);
		assertTrue(remainingPayments == 52);
	}
	
	
}
