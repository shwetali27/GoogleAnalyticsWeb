package com.bridgelabz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="summary_report")
public class SummaryDatabaseModel {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Discription")
	private String mGaDiscription;
	@Column(name="Date")
	private Integer date;
	@Column(name="TotalUsers")
	private int count;
	
	//getters and setters
	public String getmGaDiscription() {
		return mGaDiscription;
	}
	public void setmGaDiscription(String mGaDiscription) {
		this.mGaDiscription = mGaDiscription;
	}
	
	public Integer getDate() {
		return date;
	}
	
	public void setDate(Integer date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
