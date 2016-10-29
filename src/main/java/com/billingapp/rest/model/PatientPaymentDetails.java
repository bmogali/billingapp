package com.billingapp.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientPaymentDetails extends PatientDetails {

	private int patientPaymentId;
	private int creditTypeId;
	private String creditName;
	private String creditAmount;
	private String creditDate;
	private String comment;
	private int creditApplicationId;
	private String creditApplicationName;

	public int getCreditTypeId() {
		return creditTypeId;
	}

	public void setCreditTypeId(int creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	public int getCreditApplicationId() {
		return creditApplicationId;
	}

	public void setCreditApplicationId(int creditApplicationId) {
		this.creditApplicationId = creditApplicationId;
	}

	public void setPatientPaymentId(int patientPaymentid) {
		this.patientPaymentId = patientPaymentid;
	}

	public int getPatientPaymentId() {
		return patientPaymentId;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreditApplicationName() {
		return creditApplicationName;
	}

	public void setCreditApplicationName(String creditApplicationName) {
		this.creditApplicationName = creditApplicationName;
	}

}
