package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "chargetypes")
@Table(name = "chargetypes")
public class ChargeTypeDTO {

	@Id
	@GeneratedValue
	@Column(name = "ChargeTypeID")
	private int chargeId;

	@Column(name = "Charge")
	private String charge;

	@Column(name = "DefaultCharge")
	private Double defaultCharge;

	@Column(name = "Display")
	private boolean display;

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public Double getDefaultCharge() {
		return defaultCharge;
	}

	public void setDefaultCharge(Double defaultCharge) {
		this.defaultCharge = defaultCharge;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}
	
}
