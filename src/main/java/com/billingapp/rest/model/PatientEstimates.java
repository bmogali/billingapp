package com.billingapp.rest.model;


public class PatientEstimates {

	private int patientEstimatesId;
	
	private int patientId;
	
	private int chargeTypeId;
	
	private String dateOfService;
	
	private String typeOfService;
	
	private Double defaultCharge;
	
	private int discount;
	
	private Double tax;
	
	private Double totalCharge;
	
	private Double baseCharge;
	
	private String comments;
	
	private int numberOfSessions;

	public int getPatientEstimatesId() {
		return patientEstimatesId;
	}

	public void setPatientEstimatesId(int patientEstimatesId) {
		this.patientEstimatesId = patientEstimatesId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}

	public Double getDefaultCharge() {
		return defaultCharge;
	}

	public void setDefaultCharge(Double defaultCharge) {
		this.defaultCharge = defaultCharge;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	public Double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(Double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public int getChargeTypeId() {
		return chargeTypeId;
	}

	public void setChargeTypeId(int chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}
}
