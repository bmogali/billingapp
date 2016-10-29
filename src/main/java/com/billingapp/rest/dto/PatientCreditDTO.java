package com.billingapp.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity(name="patientcredits")
@Table(name="patientcredits")
public class PatientCreditDTO {
	
	@Id
	@GeneratedValue
	@Column(name="PatientCreditID")
	private int patientCreditId;
	
	@ManyToOne
	@JoinColumn(name="PatientID")
	private PatientDTO patient;
	
	@ManyToOne
	@JoinColumn(name="CreditTypeID")
	private CreditTypesDTO creditType;
	
	@Column(name="CreditAmount")
	private Double creditAmount;
	
	@Column(name="CreditDate")
	private Date creditDate;
	
	@Column(name="Comment")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="CreditApplicationID")
	
	private CreditApplicationDTO creditApplication;
	
	public int getPatientCreditId() {
		return patientCreditId;
	}

	public void setPatientCreditId(int patientCreditId) {
		this.patientCreditId = patientCreditId;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public CreditTypesDTO getCreditType() {
		return creditType;
	}

	public void setCreditType(CreditTypesDTO creditType) {
		this.creditType = creditType;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CreditApplicationDTO getCreditApplication() {
		return creditApplication;
	}

	public void setCreditApplication(CreditApplicationDTO creditApplication) {
		this.creditApplication = creditApplication;
	}



}
