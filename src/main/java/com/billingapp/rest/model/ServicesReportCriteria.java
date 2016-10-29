package com.billingapp.rest.model;

import java.util.Date;

import com.billingapp.rest.utilities.DateUtil;

public class ServicesReportCriteria {

	private String startDate;
	private String endDate;
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getStartDateInDateFormat(){
		
		return DateUtil.stringToDateTime(startDate);
	}	

	public Date getEndDateInDateFormat(){
		
		return DateUtil.stringToDateTime(endDate);
	}
	

}
