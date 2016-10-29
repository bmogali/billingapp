package com.billingapp.rest.model;

import java.util.List;

public class Reports {

	private List<PatientChargeDetails> patientChargeDetails;
	private List<PatientProductDetails> patientProductDetails;
	private List<PatientPaymentDetails> patientPaymentDetails;

	public List<PatientChargeDetails> getPatientChargeDetails() {
		return patientChargeDetails;
	}

	public void setPatientChargeDetiails(
			List<PatientChargeDetails> patientChargeDetails) {
		this.patientChargeDetails = patientChargeDetails;
	}

	public List<PatientProductDetails> getPatientProductDetails() {
		return patientProductDetails;
	}

	public void setPatientProductDetiails(
			List<PatientProductDetails> patientProductDetails) {
		this.patientProductDetails = patientProductDetails;
	}

	public List<PatientPaymentDetails> getPatientPaymentDetails() {
		return patientPaymentDetails;
	}

	public void setPatientPaymentDetiails(
			List<PatientPaymentDetails> patientPaymentDetails) {
		this.patientPaymentDetails = patientPaymentDetails;
	}

}
