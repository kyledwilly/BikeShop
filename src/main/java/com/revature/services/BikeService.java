package com.revature.services;

import java.util.ArrayList;

import com.revature.beans.Bike;
import com.revature.beans.User;

public interface BikeService {
	public Integer addBike(Bike b);
	public Bike getBikeById(Integer id);
	public void removeBike(Bike b);
	public ArrayList<Bike> listAvailableBikes();
	public ArrayList<Bike> listUserBikes(Integer userId);
}
