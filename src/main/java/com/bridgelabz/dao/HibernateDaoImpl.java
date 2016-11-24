package com.bridgelabz.dao;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bridgelabz.model.AllElementModels;

public class HibernateDaoImpl implements HibernateDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	Session session;

	// method to save all elements in database
	public void Save(ArrayList<AllElementModels> responseElementModelArrayList) {
		try {

			for (int i = 0; i < responseElementModelArrayList.size(); i++) {

				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				session.save(responseElementModelArrayList.get(i));
				tx.commit();
				session.close();
			}

		} catch (Exception e) {
			System.out.println("hello");
			e.printStackTrace();
		}
	}

}
