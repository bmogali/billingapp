package com.billingapp.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "patientestimates")
@Table(name = "patientestimates")
public class PatientEstimatesDTO {
	
	@Id
	@GeneratedValue
	@Column(name="PatientEstimatesID")
	private int patientEstimatesID;
	
	@Column(name="PatientID")
	private int patientID;
	
	@Column(name="ChargeTypeID")
	private int chargeTypeId;
	
	@Column(name="DateOfService")
	private Date dateOfService;
	
	@Column(name="TypeOfService")
	private String typeOfService;
	
	@Column(name="DefaultCharge")
	private Double defaultCharge;
	
	@Column(name="BaseCharge")
	private Double baseCharge;
	
	@Column(name="Discount")
	private int discount;
	
	@Column(name="Tax")
	private Double tax;
	
	@Column(name="TotalCharge")
	private Double totalCharge;
	
	@Column(name="Comments")
	private String comments;
	
	@Column(name="NumberOfSessions")
	private int numberOfSessions;

	public int getPatientEstimatesID() {
		return patientEstimatesID;
	}

	public void setPatientEstimatesID(int patientEstimatesID) {
		this.patientEstimatesID = patientEstimatesID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public Date getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(Date dateOfService) {
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
