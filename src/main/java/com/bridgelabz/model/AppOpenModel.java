package com.bridgelabz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AppOpenModel {
	@Override
	public String toString() {
		return "AppOpenModel [mGaId=" + mGaId + ", mGadiscription=" + mGadiscription + ", mDate=" + mDate
				+ ", mAndroidId=" + mAndroidId + ", mEventcategory=" + mEventcategory + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column
	private String mGaId;
	@Column
	private String mGadiscription;
	@Column
	private String mDate;
	@Column
	private String mAndroidId;
	@Column
	private String mEventcategory;

	public String getmGaId() {
		return mGaId;
	}

	public void setmGaId(String mGaId) {
		this.mGaId = mGaId;
	}

	public String getmGadiscription() {
		return mGadiscription;
	}

	public void setmGadiscription(String mGadiscription) {
		this.mGadiscription = mGadiscription;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getmAndroidId() {
		return mAndroidId;
	}

	public void setmAndroidId(String mAndroidId) {
		this.mAndroidId = mAndroidId;
	}

	public String getmEventcategory() {
		return mEventcategory;
	}

	public void setmEventcategory(String mEventcategory) {
		this.mEventcategory = mEventcategory;
	}
}
