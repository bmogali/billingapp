package com.billingapp.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientProductDetails extends PatientDetails {
	
	private int patientProductId;
	private int productId;
	private String productName;
	private String productBaseCharge;
	private String date;
	private String comment;
	private String productTotalCharge;
	private String productTax;
	private String quantity;
	private String discount;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getPatientProductId() {
		return patientProductId;
	}
	public void setPatientProductId(int patientProductId) {
		this.patientProductId = patientProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBaseCharge() {
		return productBaseCharge;
	}
	public void setProductBaseCharge(String productBaseCharge) {
		this.productBaseCharge = productBaseCharge;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getProductTotalCharge() {
		return productTotalCharge;
	}
	public void setProductTotalCharge(String productTotalCharge) {
		this.productTotalCharge = productTotalCharge;
	}
	public String getProductTax() {
		return productTax;
	}
	public void setProductTax(String productTax) {
		this.productTax = productTax;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
