package com.billingapp.rest.model;


public class ProcedureAreas {
	
	private int procedureAreaId;
	
	private int patientProcedureId;

	private Areas areas;
	
	private String baselineTemp;
	
	private String fluence;
	
	private String peakTemp;

	public int getProcedureAreaId() {
		return procedureAreaId;
	}

	public void setProcedureAreaId(int procedureAreaId) {
		this.procedureAreaId = procedureAreaId;
	}

	public int getPatientProcedureId() {
		return patientProcedureId;
	}

	public void setPatientProcedureId(int patientProcedureId) {
		this.patientProcedureId = patientProcedureId;
	}

	public Areas getAreas() {
		return areas;
	}

	public void setAreas(Areas areas) {
		this.areas = areas;
	}

	public String getBaselineTemp() {
		return baselineTemp;
	}

	public void setBaselineTemp(String baselineTemp) {
		this.baselineTemp = baselineTemp;
	}

	public String getFluence() {
		return fluence;
	}

	public void setFluence(String fluence) {
		this.fluence = fluence;
	}

	public String getPeakTemp() {
		return peakTemp;
	}

	public void setPeakTemp(String peakTemp) {
		this.peakTemp = peakTemp;
	}


}
