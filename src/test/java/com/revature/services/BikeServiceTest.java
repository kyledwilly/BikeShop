package com.revature.services;

import com.revature.beans.Bike;
import com.revature.beans.BikeStatus;

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

class BikeServiceTest{
	BikeService bikeServ =  new BikeServiceImpl();
	private static Integer newBikeId = null;
	private static Bike bike;
	
@Test
//public Bike getBikeById(Integer id);
//public Integer addBike(Bike b);
public void testAddBikeAndGetBikeById() {
	bike = new Bike();
	bike.setBrand("Huffy");
	bike.setColor("Black");
	bike.setYear("1980");
	BikeStatus status = new BikeStatus();
	bike.setStatus(status);
	bike.setOwnerID(5);
	newBikeId = bikeServ.addBike(bike);
	assertTrue(newBikeId > 0);
	
	Bike bike2 = bikeServ.getBikeById(newBikeId);
	
	assertTrue(bike.getBrand().equalsIgnoreCase(bike2.getBrand()));
	assertTrue(bike.getColor().equalsIgnoreCase(bike2.getColor()));
	assertTrue(bike.getYear().equalsIgnoreCase(bike2.getYear()));
}	

//public ArrayList<Bike> listUserBikes(Integer userId);
//depends on success of above test
public void testListUserBikes() {
	ArrayList<Bike> bikes = new ArrayList<Bike>();
	bikes = bikeServ.listUserBikes(5);
	assertTrue(bikes.size() > 0);
}

//public void removeBike(Bike b);
@Test
public void testRemoveBike() {
	bikeServ.removeBike(bike);
	Bike bike2 = bikeServ.getBikeById(newBikeId);
	assertTrue(bike2 == null);
	
}
//public ArrayList<Bike> listAvailableBikes();
@Test
public void testListAvailableBikes() {
	ArrayList<Bike> bikes = new ArrayList<Bike>();
	bikes = bikeServ.listAvailableBikes();
	assertTrue(bikes.size() > 0);
}



}