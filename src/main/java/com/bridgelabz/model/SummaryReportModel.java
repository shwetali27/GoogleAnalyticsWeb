package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SummaryReportModel {
	private List<Integer> dates = new ArrayList<Integer>();
	private List<Integer> totalCount = new ArrayList<Integer>();
	private String mGaDiscription;

	// getters and setters
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
