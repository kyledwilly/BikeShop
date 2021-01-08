package com.revature.beans;

public class Payment {
	private Integer id;
	private String payment_date;
	private String finance_id;
	private Finance finance;
	
	public Finance getFinance() {
		return finance;
	}
	public void setFinance(Finance finance) {
		this.finance = finance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getFinance_id() {
		return finance_id;
	}
	public void setFinance_id(String finance_id) {
		this.finance_id = finance_id;
	}
}
