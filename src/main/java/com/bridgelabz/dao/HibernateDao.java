package com.bridgelabz.dao;

import java.util.ArrayList;

import com.bridgelabz.model.SummaryDatabaseModel;

public interface HibernateDao {
	public void drop();
	public void save(ArrayList<SummaryDatabaseModel> summaryDatabaseModellist);
	
}
