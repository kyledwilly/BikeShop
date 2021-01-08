package com.revature.data;

import java.util.Set;

public interface GenericDAO <T>{
	//CRUDL operations	
	
	//create
	public T create(T t) throws Exception;
	
	//read
	public T read(Integer id);
	
	//update
	public void update(T t);
	
	//delete
	public void delete();
	
	//list
	public Set<T> List();
	
}
