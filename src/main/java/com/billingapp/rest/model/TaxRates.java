package com.billingapp.rest.model;


public class TaxRates {
	
	private int taxRateId;
	
	private String taxItem;

	private Double taxRate;

	public int getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(int taxRateId) {
		this.taxRateId = taxRateId;
	}

	public String getTaxItem() {
		return taxItem;
	}

	public void setTaxItem(String taxItem) {
		this.taxItem = taxItem;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
}
