package com.sunyahui.b_dao;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		// Configuration cfg = new Configuration();
		// cfg.configure("hibernate.cfg.xml");
		// sessionFactory = cfg.buildSessionFactory();
		sessionFactory = new Configuration()//
				.configure()//
				.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

}
