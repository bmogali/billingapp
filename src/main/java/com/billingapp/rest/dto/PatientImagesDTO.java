package com.billingapp.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="patientimages")
@Table(name="patientimages")
public class PatientImagesDTO {

	@Id
	@GeneratedValue
	@Column(name="PatientImagesID")
	private int patientImagesID;
	
	@Column(name="PatientID")
	private int patientID;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="ImageName")
	private String imageName;
	
	@Column(name="FileName")
	private String fileName;
	
	@Column(name = "Profile")
	private boolean profile;

	public int getPatientImagesID() {
		return patientImagesID;
	}

	public void setPatientImagesID(int patientImagesID) {
		this.patientImagesID = patientImagesID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isProfile() {
		return profile;
	}

	public void setProfile(boolean profile) {
		this.profile = profile;
	}
	
}
