package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.List;

public class SummaryReportModel {
	List<Integer> dates = new ArrayList<Integer>();
	List<Integer> totalCount = new ArrayList<Integer>();
	String mGaDiscription;
	
	//getters and setters
	public List<Integer> getDates() {
		return dates;
	}
	public void setDates(List<Integer> dates) {
		this.dates = dates;
	}
	public List<Integer> getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(List<Integer> totalCount) {
		this.totalCount = totalCount;
	}
	public String getmGaDiscription() {
		return mGaDiscription;
	}
	public void setmGaDiscription(String mGaDiscription) {
		this.mGaDiscription = mGaDiscription;
	}
	
}
