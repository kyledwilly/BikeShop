package com.revature.beans;

public class BikeStatus {
private String id;
private String status;
public BikeStatus() {
	id = "3";
	status = "available";
}
public BikeStatus(String id) {
	if(id == "3") {
		id = "3";
		status = "available";	
	}
	else if(id == "4") {
		id = "4";
		status = "unavailable";	
	}
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}