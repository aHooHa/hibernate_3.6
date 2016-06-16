package com.sunyahui.d_id;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	
	
	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(User.class)//
			.buildSessionFactory();
	
	@Test
	public void testsave() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			User user = new User();
			user.setName("hauhua");
			session.save(user);
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
		
	}
}
