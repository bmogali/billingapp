package com.billingapp.rest.model;

public class ChargeType {

	private Integer chargeTypeId;
	private String chargeName;
	private Double defaultCharge;
	private boolean display;

	public Integer getChargeTypeId() {
		return chargeTypeId;
	}

	public void setChargeTypeId(Integer chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
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
