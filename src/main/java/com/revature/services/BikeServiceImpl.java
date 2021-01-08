package com.revature.services;

import java.util.ArrayList;

import com.revature.beans.Bike;
import com.revature.beans.User;
import com.revature.data.BikeDAO;
import com.revature.data.BikeDAOFactory;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;

public class BikeServiceImpl implements BikeService {
	private BikeDAO bikeDAO;
	
	
	public BikeServiceImpl() {
		BikeDAOFactory bikeDAOFactory = new BikeDAOFactory();
		bikeDAO = bikeDAOFactory.getBikeDAOFactory();
	}
	
	@Override
	public Integer addBike(Bike b) {
		try{
			Bike newBike = bikeDAO.create(b);
			
			return newBike.getId();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Bike getBikeById(Integer id) {
		return bikeDAO.read(id);
	}
	
	@Override
	public void removeBike(Bike b) {
		bikeDAO.delete(b);
	}

	@Override
	public ArrayList<Bike> listAvailableBikes() {
		return bikeDAO.listAvailable();
	}

	@Override
	public ArrayList<Bike> listUserBikes(Integer userId) {
		return bikeDAO.listUserBikes(userId);
	}

}
