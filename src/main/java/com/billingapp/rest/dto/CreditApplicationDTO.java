package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="creditapplications")
public class CreditApplicationDTO {
	
	@Id
	@GeneratedValue
	@Column(name="CreditApplicationID")
	private int creditApplicationID;
	
	@Column(name="CreditApplication")
	private String creditApplication;

	public int getCreditApplicationID() {
		return creditApplicationID;
	}

	public void setCreditApplicationID(int creditApplicationID) {
		this.creditApplicationID = creditApplicationID;
	}

	public String getCreditApplication() {
		return creditApplication;
	}

	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	

}
