package com.billingapp.rest.model.Form;

public class Form {
	
	private int FormId;
	private String formName;
	private String formReport;
	
	public int getFormId() {
		return FormId;
	}
	public void setFormId(int formId) {
		FormId = formId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormReport() {
		return formReport;
	}
	public void setFormReport(String formReport) {
		this.formReport = formReport;
	}
	
	

}
