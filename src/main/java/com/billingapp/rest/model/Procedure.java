package com.billingapp.rest.model;


public class Procedure {
	
    private Integer procedureId;
     
    private String procedureName;
    
    private String procedureForm;
    
    private String procedureReport;

	public Integer getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
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
