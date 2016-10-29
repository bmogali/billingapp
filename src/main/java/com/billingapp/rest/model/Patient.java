package com.billingapp.rest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

	private int patientId;
	
	private List<PatientProcedures> patientProcedures; 
	
	private PatientBillingDetails patientBillingDetails;

	private String firstName;

	private String lastName;

	private String dob;
	
	private String email;

	private String mailingAddress1;

	private String mailingAddress2;

	private String mailingCity;

	private String mailingState;

	private String mailingZip;

	private String mobileNumber;
	
	private String homePhoneNumber;

	private String workPhoneNumber;

	private String allergies;

	private String medicalHistory;

	private String sex;

	private String maritalStatus;

	private String socSec;

	private String driversLicenseNum;

	private String pharmacy;

	private String spousePartner;

	private String referredBy;

	private String primaryCare;

	private String guarantorName;

	private String guarantorDOB;

	private String guarantorSocSec;

	private String guarantorPhone;

	private String guarantorAddress;

	private String guarantorCity;

	private String guarantorState;

	private String guarantorZip;

	private String employer;

	private String workAddress;

	private String workPhone;

	private String workCity;

	private String workState;

	private String workZip;

	private String primaryInsurance;

	private String primaryPlan;

	private String primarySusbscriberName;

	private String primaryPatientRelationshiptoSubsc;

	private String primaryPolicyId;

	private String primaryGroupNumber;

	private String secondaryInsurance;

	private String secondaryPlan;

	private String secondarySusbscriberName;

	private String secondaryPatientRelationshiptoSubsc;

	private String secondaryPolicyId;

	private String secondaryGroupNumber;

	private String otherInsurance;

	private String images;

	private String pictureLocation;

	private String appointment;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMailingAddress1() {
		return mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public String getMailingCity() {
		return mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingState() {
		return mailingState;
	}

	public void setMailingState(String mailingState) {
		this.mailingState = mailingState;
	}

	public String getMailingZip() {
		return mailingZip;
	}

	public void setMailingZip(String mailingZip) {
		this.mailingZip = mailingZip;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSocSec() {
		return socSec;
	}

	public void setSocSec(String socSec) {
		this.socSec = socSec;
	}

	public String getDriversLicenseNum() {
		return driversLicenseNum;
	}

	public void setDriversLicenseNum(String driversLicenseNum) {
		this.driversLicenseNum = driversLicenseNum;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getSpousePartner() {
		return spousePartner;
	}

	public void setSpousePartner(String spousePartner) {
		this.spousePartner = spousePartner;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getPrimaryCare() {
		return primaryCare;
	}

	public void setPrimaryCare(String primaryCare) {
		this.primaryCare = primaryCare;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public String getGuarantorDOB() {
		return guarantorDOB;
	}

	public void setGuarantorDOB(String guarantorDOB) {
		this.guarantorDOB = guarantorDOB;
	}

	public String getGuarantorSocSec() {
		return guarantorSocSec;
	}

	public void setGuarantorSocSec(String guarantorSocSec) {
		this.guarantorSocSec = guarantorSocSec;
	}

	public String getGuarantorPhone() {
		return guarantorPhone;
	}

	public void setGuarantorPhone(String guarantorPhone) {
		this.guarantorPhone = guarantorPhone;
	}

	public String getGuarantorAddress() {
		return guarantorAddress;
	}

	public void setGuarantorAddress(String guarantorAddress) {
		this.guarantorAddress = guarantorAddress;
	}

	public String getGuarantorCity() {
		return guarantorCity;
	}

	public void setGuarantorCity(String guarantorCity) {
		this.guarantorCity = guarantorCity;
	}

	public String getGuarantorState() {
		return guarantorState;
	}

	public void setGuarantorState(String guarantorState) {
		this.guarantorState = guarantorState;
	}

	public String getGuarantorZip() {
		return guarantorZip;
	}

	public void setGuarantorZip(String guarantorZip) {
		this.guarantorZip = guarantorZip;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getWorkZip() {
		return workZip;
	}

	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	public String getPrimaryInsurance() {
		return primaryInsurance;
	}

	public void setPrimaryInsurance(String primaryInsurance) {
		this.primaryInsurance = primaryInsurance;
	}

	public String getPrimaryPlan() {
		return primaryPlan;
	}

	public void setPrimaryPlan(String primaryPlan) {
		this.primaryPlan = primaryPlan;
	}

	public String getPrimarySusbscriberName() {
		return primarySusbscriberName;
	}

	public void setPrimarySusbscriberName(String primarySusbscriberName) {
		this.primarySusbscriberName = primarySusbscriberName;
	}

	public String getPrimaryPatientRelationshiptoSubsc() {
		return primaryPatientRelationshiptoSubsc;
	}

	public void setPrimaryPatientRelationshiptoSubsc(
			String primaryPatientRelationshiptoSubsc) {
		this.primaryPatientRelationshiptoSubsc = primaryPatientRelationshiptoSubsc;
	}

	public String getPrimaryPolicyId() {
		return primaryPolicyId;
	}

	public void setPrimaryPolicyId(String primaryPolicyId) {
		this.primaryPolicyId = primaryPolicyId;
	}

	public String getPrimaryGroupNumber() {
		return primaryGroupNumber;
	}

	public void setPrimaryGroupNumber(String primaryGroupNumber) {
		this.primaryGroupNumber = primaryGroupNumber;
	}

	public String getSecondaryInsurance() {
		return secondaryInsurance;
	}

	public void setSecondaryInsurance(String secondaryInsurance) {
		this.secondaryInsurance = secondaryInsurance;
	}

	public String getSecondaryPlan() {
		return secondaryPlan;
	}

	public void setSecondaryPlan(String secondaryPlan) {
		this.secondaryPlan = secondaryPlan;
	}

	public String getSecondarySusbscriberName() {
		return secondarySusbscriberName;
	}

	public void setSecondarySusbscriberName(String secondarySusbscriberName) {
		this.secondarySusbscriberName = secondarySusbscriberName;
	}

	public String getSecondaryPatientRelationshiptoSubsc() {
		return secondaryPatientRelationshiptoSubsc;
	}

	public void setSecondaryPatientRelationshiptoSubsc(
			String secondaryPatientRelationshiptoSubsc) {
		this.secondaryPatientRelationshiptoSubsc = secondaryPatientRelationshiptoSubsc;
	}

	public String getSecondaryPolicyId() {
		return secondaryPolicyId;
	}

	public void setSecondaryPolicyId(String secondaryPolicyId) {
		this.secondaryPolicyId = secondaryPolicyId;
	}

	public String getSecondaryGroupNumber() {
		return secondaryGroupNumber;
	}

	public void setSecondaryGroupNumber(String secondaryGroupNumber) {
		this.secondaryGroupNumber = secondaryGroupNumber;
	}

	public String getOtherInsurance() {
		return otherInsurance;
	}

	public void setOtherInsurance(String otherInsurance) {
		this.otherInsurance = otherInsurance;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPictureLocation() {
		return pictureLocation;
	}

	public void setPictureLocation(String pictureLocation) {
		this.pictureLocation = pictureLocation;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public List<PatientProcedures> getPatientProcedures() {
		return patientProcedures;
	}

	public void setPatientProcedures(List<PatientProcedures> patientProcedures) {
		this.patientProcedures = patientProcedures;
	}
	
	public PatientBillingDetails getPatientBillingDetails() {
		return patientBillingDetails;
	}

	public void setPatientBillingDetails(PatientBillingDetails patientBillingDetails) {
		this.patientBillingDetails = patientBillingDetails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
