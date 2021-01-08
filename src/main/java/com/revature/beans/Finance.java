package com.revature.beans;

import java.util.ArrayList;

public class Finance {
	private Integer balance;
	private Integer paymentAmount;
	private Integer remainingPayments;
	private Integer bike_id;
	private Integer user_id;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private ArrayList<Payment> payments;
	
	public ArrayList<Payment> getPayments() {
		return payments;
	}

	public void setPayments(ArrayList<Payment> payments) {
		this.payments = payments;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getRemainingPayments() {
		return remainingPayments;
	}

	public void setRemainingPayments(Integer remainingPayments) {
		this.remainingPayments = remainingPayments;
	}

	public Integer getBike_id() {
		return bike_id;
	}

	public void setBike_id(Integer bike_id) {
		this.bike_id = bike_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
