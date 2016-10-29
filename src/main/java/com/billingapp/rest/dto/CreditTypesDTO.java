package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "credittypes")
public class CreditTypesDTO {

	@Id
	@GeneratedValue
	@Column(name = "CreditTypeID")
	private int creditTypeId;

	@Column(name = "CreditType")
	private String creditType;

	@Column(name = "Display")
	private boolean display;

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public int getCreditTypeId() {
		return creditTypeId;
	}

	public void setCreditTypeId(int creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

}
