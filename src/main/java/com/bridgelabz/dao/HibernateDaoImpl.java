package com.bridgelabz.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bridgelabz.model.SummaryDatabaseModel;

public class HibernateDaoImpl implements HibernateDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	public void truncate() {
		try {
			System.out.println("delete data");
			Session session = sessionFactory.openSession();
			session.createSQLQuery("TRUNCATE summary_report").executeUpdate();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// method to save all elements in database
	public void save(ArrayList<SummaryDatabaseModel> summaryDatabaseModellist) {
		try {
			
			Session session = sessionFactory.openSession();
			for (int i = 0; i < summaryDatabaseModellist.size(); i++) {
				System.out.println(summaryDatabaseModellist.get(i).getDate());
				
				Transaction tx = session.beginTransaction();
				session.save(summaryDatabaseModellist.get(i));
				tx.commit();
				
			}
			session.close();
		} catch (Exception e) {
			System.out.println("hello");
			e.printStackTrace();
		}
	}

}
