package com.billingapp.rest.model;

public class CreditType {

	private Integer creditTypeId;
	private String creditName;
	private boolean display;

	public Integer getCreditTypeId() {
		return creditTypeId;
	}

	public void setCreditTypeId(Integer creditType) {
		this.creditTypeId = creditType;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

}
