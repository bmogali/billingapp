package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "procedureareas")
public class ProcedureAreasDTO {
	
	@Id
	@GeneratedValue
	@Column(name = "ProcedureAreaID")
	private int procedureAreaId;
	
	@ManyToOne
	@JoinColumn(name = "PatientProcedureID")
	private PatientProcedureDTO patientProcedure;

	@OneToOne
	@JoinColumn(name = "AreaId")
	@Cascade(CascadeType.DELETE)
	private AreasDTO areas;
	
	@Column(name = "BaselineTemp")
	private String baselineTemp;
	
	@Column(name = "Fluence")
	private String fluence;
	
	@Column(name = "PeakTemp")
	private String peakTemp;

	public int getProcedureAreaId() {
		return procedureAreaId;
	}

	public void setProcedureAreaId(int procedureAreaId) {
		this.procedureAreaId = procedureAreaId;
	}

	public PatientProcedureDTO getPatientProcedure() {
		return patientProcedure;
	}

	public void setPatientProcedure(PatientProcedureDTO patientProcedure) {
		this.patientProcedure = patientProcedure;
	}

	public AreasDTO getAreas() {
		return areas;
	}

	public void setAreas(AreasDTO areas) {
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
