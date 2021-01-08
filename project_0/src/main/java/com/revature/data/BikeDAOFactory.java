package com.revature.data;

public class BikeDAOFactory {
	public BikeDAO getBikeDAOFactory() {
		return new BikePostgres();
	}
}
