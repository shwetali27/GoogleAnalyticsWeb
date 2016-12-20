package com.bridgelabz.model;

import org.hibernate.validator.constraints.NotEmpty;

public class DateData {
	@NotEmpty(message = "Please enter start date.")
	private String startDate;
	
	@NotEmpty(message = "Please enter end date.")
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
