package com.billingapp.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity(name="patientcharges")
@Table(name="patientcharges")
public class PatientChargeDTO {
	
	@Id
	@GeneratedValue
	@Column(name="PatientChargeID")
	private int patientChargeID;
	
	@ManyToOne
	@JoinColumn(name="PatientID")
	@NotFound(action = NotFoundAction.IGNORE)
	private PatientDTO patient;
	
	@ManyToOne
	@JoinColumn(name="ChargeTypeID")
	private ChargeTypeDTO chargeType;
	
	@Column(name="ChargeDate")
	private Date chargeDate;
	
	@Column(name="NumberofSessions")
	private Integer numberofSessions;
	
	@Column(name="BaseCharge")
	private Double baseCharge;
	
	@Column(name="Comment")
	private String comment;
	
	@Column(name="TotalCharge")
	private Double totalCharge;
	
	@Column(name="Tax")
	private Double tax;
	
	@Column(name="Discount")
	private Double discount;
	
	public int getPatientChargeID() {
		return patientChargeID;
	}

	public void setPatientChargeID(int patientChargeID) {
		this.patientChargeID = patientChargeID;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public ChargeTypeDTO getChargeType() {
		return chargeType;
	}

	public void setChargeType(ChargeTypeDTO chargeType) {
		this.chargeType = chargeType;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Integer getNumberofSessions() {
		return numberofSessions;
	}

	public void setNumberofSessions(Integer numberofSessions) {
		this.numberofSessions = numberofSessions;
	}

	public Double getBaseCharge() {
		return baseCharge;
	}

	public void setBaseCharge(Double baseCharge) {
		this.baseCharge = baseCharge;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
}


