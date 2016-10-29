package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patientforms")

public class FormDTO {
		@Id
		@GeneratedValue
		@Column(name="FormID")
		 private int formID;
		 
		 @Column(name="FormName")
		 private String formName;
		 
		 @Column(name="FormReport")
		 private String formReport;

		public int getFormID() {
			return formID;
		}

		public void setFormID(int formID) {
			this.formID = formID;
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
