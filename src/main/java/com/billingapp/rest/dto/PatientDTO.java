package com.billingapp.rest.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity(name = "patients")
@Table(name = "patients")
public class PatientDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PatientID")
	private Integer patientID;
	
	@OneToMany(mappedBy="patient")
	@Cascade(CascadeType.DELETE)
	@OrderBy("procedureDate DESC")
	private Set<PatientProcedureDTO> patientProcedures;
	

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "`Last Name`")
	private String lastName;

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "Email")
	private String email;

	@Column(name = "MailingAddress1")
	private String mailingAddress1;

	@Column(name = "MailingAddress2")
	private String mailingAddress2;

	@Column(name = "MailingCity")
	private String mailingCity;

	@Column(name = "MailingState")
	private String mailingState;

	@Column(name = "MailingZip")
	private String mailingZip;

	@Column(name = "MobileNumber")
	private String mobileNumber;
	
	@Column(name = "HomePhoneNumber")
	private String homePhoneNumber;

	@Column(name = "WorkPhoneNumber")
	private String workPhoneNumber;

	@Column(name = "Allergies")
	private String allergies;

	@Column(name = "`Medical History`", length = 100000)
	private String medicalHistory;

	@Column(name = "Sex")
	private String sex;

	@Column(name = "MaritalStatus")
	private String maritalStatus;

	@Column(name = "SocSec")
	private String socSec;

	@Column(name = "DriversLicenseNum")
	private String driversLicenseNum;

	@Column(name = "Pharmacy")
	private String pharmacy;

	@Column(name = "SpousePartner")
	private String spousePartner;

	@Column(name = "ReferredBy")
	private String referredBy;

	@Column(name = "PrimaryCare")
	private String primaryCare;

	@Column(name = "GuarantorName")
	private String guarantorName;

	@Temporal(TemporalType.DATE)
	@Column(name = "GuarantorDOB")
	private Date guarantorDOB;

	@Column(name = "GuarantorSocSec")
	private String guarantorSocSec;

	@Column(name = "GuarantorPhone")
	private String guarantorPhone;

	@Column(name = "GuarantorAddress")
	private String guarantorAddress;

	@Column(name = "GuarantorCity")
	private String guarantorCity;

	@Column(name = "GuarantorState")
	private String guarantorState;

	@Column(name = "GuarantorZip")
	private String guarantorZip;

	@Column(name = "Employer")
	private String employer;

	@Column(name = "WorkAddress")
	private String workAddress;

	@Column(name = "WorkPhone")
	private String workPhone;

	@Column(name = "WorkCity")
	private String workCity;

	@Column(name = "WorkState")
	private String workState;

	@Column(name = "WorkZip")
	private String workZip;

	@Column(name = "PrimaryInsurance")
	private String primaryInsurance;

	@Column(name = "PrimaryPlan")
	private String primaryPlan;

	@Column(name = "PrimarySusbscriberName")
	private String primarySusbscriberName;

	@Column(name = "PrimaryPatientRelationshiptoSubsc")
	private String primaryPatientRelationshiptoSubsc;

	@Column(name = "PrimaryPolicyID")
	private String primaryPolicyID;

	@Column(name = "PrimaryGroupNumber")
	private String primaryGroupNumber;

	@Column(name = "SecondaryInsurance")
	private String secondaryInsurance;

	@Column(name = "SecondaryPlan")
	private String secondaryPlan;

	@Column(name = "SecondarySusbscriberName")
	private String secondarySusbscriberName;

	@Column(name = "SecondaryPatientRelationshiptoSubsc")
	private String secondaryPatientRelationshiptoSubsc;

	@Column(name = "SecondaryPolicyID")
	private String secondaryPolicyID;

	@Column(name = "SecondaryGroupNumber")
	private String secondaryGroupNumber;

	@Column(name = "OtherInsurance")
	private String otherInsurance;

	@Column(name = "Images")
	private String images;

	@Column(name = "PictureLocation")
	private String pictureLocation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Appointment")
	private Date appointment;

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public Date getGuarantorDOB() {
		return guarantorDOB;
	}

	public void setGuarantorDOB(Date guarantorDOB) {
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

	public String getPrimaryPolicyID() {
		return primaryPolicyID;
	}

	public void setPrimaryPolicyID(String primaryPolicyID) {
		this.primaryPolicyID = primaryPolicyID;
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

	public String getSecondaryPolicyID() {
		return secondaryPolicyID;
	}

	public void setSecondaryPolicyID(String secondaryPolicyID) {
		this.secondaryPolicyID = secondaryPolicyID;
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

	public Date getAppointment() {
		return appointment;
	}

	public void setAppointment(Date appointment) {
		this.appointment = appointment;
	}

	public Set<PatientProcedureDTO> getPatientProcedures() {
		return patientProcedures;
	}

	public void setPatientProcedures(Set<PatientProcedureDTO> patientProcedures) {
		this.patientProcedures = patientProcedures;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
