package com.bridgelabz.model;

import java.util.ArrayList;

public class GaReportInputModel {
	private String mGaID;
	private String mGaDiscription;
	private ArrayList<String> mMetricArraList;
	private ArrayList<String> mDimensionArraList;
	private ArrayList<String> mDimensionFilterArraList;
	
	// setter and getter method

	public void setmGaID(String mGaID) {
		this.mGaID = mGaID;
	}

	public void setmGaDiscription(String mGaDiscription) {
		this.mGaDiscription = mGaDiscription;
	}

	public void setmMetricArraList(ArrayList<String> mMetricArraList) {
		this.mMetricArraList = mMetricArraList;
	}

	public void setmDimensionArraList(ArrayList<String> mDimensionArraList) {
		this.mDimensionArraList = mDimensionArraList;
	}

	public void setmDimensionFilterArraList(ArrayList<String> mDimensionFilterArraList) {
		this.mDimensionFilterArraList = mDimensionFilterArraList;
	}

	public String getmGaID() {
		return mGaID;
	}

	public String getmGaDiscription() {
		return mGaDiscription;
	}

	public ArrayList<String> getmMetricArraList() {
		return mMetricArraList;
	}

	public ArrayList<String> getmDimensionArraList() {
		return mDimensionArraList;
	}

	public ArrayList<String> getmDimensionFilterArraList() {
		return mDimensionFilterArraList;
	}

}
