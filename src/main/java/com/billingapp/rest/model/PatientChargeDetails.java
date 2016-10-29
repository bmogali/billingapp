package com.billingapp.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientChargeDetails extends PatientDetails {
	
	private int patientChargeId;
	private int chargeTypeId;
	private String chargeName;
	private String chargeDate;
	private Integer numberOfSessions;
	private String baseCharge;
	private String comment;
	private String totalCharge;
	private String tax;
	private String discount;
	
	public int getChargeTypeId() {
		return chargeTypeId;
	}
	public void setChargeTypeId(int chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}
	
	public int getPatientChargeId() {
		return patientChargeId;
	}
	public void setPatientChargeId(int patientChargeId) {
		this.patientChargeId = patientChargeId;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public String getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}
	public Integer getNumberOfSessions() {
		return numberOfSessions;
	}
	public void setNumberOfSessions(Integer numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}
	public String getBaseCharge() {
		return baseCharge;
	}
	public void setBaseCharge(String baseCharge) {
		this.baseCharge = baseCharge;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
