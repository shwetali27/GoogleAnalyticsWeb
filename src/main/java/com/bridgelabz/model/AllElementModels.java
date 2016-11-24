package com.bridgelabz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AllElementModels {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column
	private String mGaid;
	@Column
	private String mGadiscription;;
	@Column
	private String mAndroidId;
	@Column
	private String mDate;
	
	//getters and setters
	public String getmGaid() {
		return mGaid;
	}
	public void setmGaid(String mGaid) {
		this.mGaid = mGaid;
	}
	public String getmGadiscription() {
		return mGadiscription;
	}
	public void setmGadiscription(String mGadiscription) {
		this.mGadiscription = mGadiscription;
	}
	public String getmAndroidId() {
		return mAndroidId;
	}
	public void setmAndroidId(String mAndroidId) {
		this.mAndroidId = mAndroidId;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	
	@Override
	public String toString() {
		return "AllElementModels [mGaid=" + mGaid + ", mGadiscription=" + mGadiscription + ", mAndroidId=" + mAndroidId
				+ ", mDate=" + mDate + "]";
	}
}
