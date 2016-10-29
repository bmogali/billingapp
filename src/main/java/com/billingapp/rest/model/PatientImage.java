package com.billingapp.rest.model;


public class PatientImage {

	private int patientImagesID;
	
	private int patientID;
	
	private String description;
	
	private String date;
	
	private String imageName;
	
	private String fileName;
	
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
