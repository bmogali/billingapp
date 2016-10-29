package com.billingapp.rest.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="procedures")
public class ProcedureDTO {
	
	@Id
	@Column(name="ProcedureID")
    @GeneratedValue
    private Integer procedureID;
     
    @Column(name="ProcedureName")
    private String procedureName;
    
    @Column(name="ProcedureForm")
    private String procedureForm;
    
    @Column(name="ProcedureReport")
    private String procedureReport;
    
    @OneToMany(mappedBy="procedureDTO")
    private Set<PatientProcedureDTO> patientProceduresDTO;

	public Integer getProcedureID() {
		return procedureID;
	}

	public void setProcedureID(Integer procedureID) {
		this.procedureID = procedureID;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProcedureForm() {
		return procedureForm;
	}

	public void setProcedureForm(String procedureForm) {
		this.procedureForm = procedureForm;
	}

	public String getProcedureReport() {
		return procedureReport;
	}

	public void setProcedureReport(String procedureReport) {
		this.procedureReport = procedureReport;
	}
    
}
