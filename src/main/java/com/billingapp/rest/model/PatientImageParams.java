package com.billingapp.rest.model;

import java.io.InputStream;

public class PatientImageParams {
	
	private InputStream inputStream;
	private int patientId;
	private String imageDescription;
	private String fileName;
	private boolean profile;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
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
