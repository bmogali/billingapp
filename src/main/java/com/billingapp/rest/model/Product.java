package com.billingapp.rest.model;

public class Product {

	private Integer productId;
	private String name;
	private Double defaultCharge;
	private boolean display;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDefaultCharge() {
		return defaultCharge;
	}
	public void setDefaultCharge(Double defaultCharge) {
		this.defaultCharge = defaultCharge;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	
	
}
