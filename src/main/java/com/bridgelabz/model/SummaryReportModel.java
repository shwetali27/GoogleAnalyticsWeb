package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SummaryReportModel {
	private List<String> dates = new ArrayList<String>();
	private List<Integer> totalCount = new ArrayList<Integer>();
	private String mGaDiscription;

	// getters and setters
	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates2) {
		this.dates = dates2;
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

	// comparing the list values
	public static Comparator<SummaryReportModel> valueComparator = new Comparator<SummaryReportModel>() {

		public int compare(SummaryReportModel s1, SummaryReportModel s2) {
			Integer value1 = s1.getTotalCount().get(0);
			Integer value2 = s2.getTotalCount().get(0);

			// decending order
			return value2.compareTo(value1);

		}
	};
}
