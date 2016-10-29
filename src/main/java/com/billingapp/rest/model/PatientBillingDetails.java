package com.billingapp.rest.model;

import java.util.List;

public class PatientBillingDetails {
	
	private List<PatientChargeDetails> patientChargeDetails;
	private List<PatientProductDetails> patientProductDetails;
	private List<PatientPaymentDetails> patientPaymentDetails;
	private String totalAmountForServices;
	private String totalAmountForProducts;
	private String totalAmountPaidForServices;
	private String totalAmountPaidForProducts;
	private String totalAmount;
	private String totalAmountLeft;
	private String totalAmountPaid;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalAmountPaid() {
		return totalAmountPaid;
	}

	public void setTotalAmountPaid(String totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	public String getTotalAmountForServices() {
		return totalAmountForServices;
	}

	public void setTotalAmountForServices(String totalAmountForServices) {
		this.totalAmountForServices = totalAmountForServices;
	}

	public String getTotalAmountForProducts() {
		return totalAmountForProducts;
	}

	public void setTotalAmountForProducts(String totalAmountForProducts) {
		this.totalAmountForProducts = totalAmountForProducts;
	}

	public String getTotalAmountPaidForServices() {
		return totalAmountPaidForServices;
	}

	public void setTotalAmountPaidForServices(String totalAmountPaidForServices) {
		this.totalAmountPaidForServices = totalAmountPaidForServices;
	}

	public String getTotalAmountPaidForProducts() {
		return totalAmountPaidForProducts;
	}

	public void setTotalAmountPaidForProducts(String totalAmountPaidForProducts) {
		this.totalAmountPaidForProducts = totalAmountPaidForProducts;
	}

	public String getTotalAmountLeft() {
		return totalAmountLeft;
	}

	public void setTotalAmountLeft(String totalAmountLeft) {
		this.totalAmountLeft = totalAmountLeft;
	}

	public List<PatientChargeDetails> getPatientChargeDetails() {
		return patientChargeDetails;
	}

	public void setPatientChargeDetails(List<PatientChargeDetails> patientChargeDetails) {
		this.patientChargeDetails = patientChargeDetails;
	}

	public List<PatientProductDetails> getPatientProductDetails() {
		return patientProductDetails;
	}

	public void setPatientProductDetails(List<PatientProductDetails> patientProductDetails) {
		this.patientProductDetails = patientProductDetails;
	}

	public List<PatientPaymentDetails> getPatientPaymentDetails() {
		return patientPaymentDetails;
	}

	public void setPatientPaymentDetails(List<PatientPaymentDetails> patientPaymentDetails) {
		this.patientPaymentDetails = patientPaymentDetails;
	}
	

}
