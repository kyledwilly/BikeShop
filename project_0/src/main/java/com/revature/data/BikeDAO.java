package com.revature.data;

import java.util.ArrayList;
import java.util.Set;

import com.revature.beans.Bike;

public interface BikeDAO extends GenericDAO<Bike> {
//create
	public Bike create(Bike b) throws Exception;
	
//read
	public Bike read(Integer id);
	
//update
	public void update(Bike b);
	
//delete
	public void delete(Bike b);
	
//list
	public Set<Bike> List();
	
	public ArrayList<Bike> listAvailable();
}
