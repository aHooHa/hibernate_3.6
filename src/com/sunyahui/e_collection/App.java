package com.sunyahui.e_collection;


import java.util.HashSet;
import java.util.Set;

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
			user.setName("王八蛋");
			user.getAddressSet().add("1天心区");
			user.getAddressSet().add("2林科大");
			user.getAddressList().add("海南省");
			user.getAddressList().add("三亚市");
			user.getAddressList().add("海南省");
			user.getAddressMap().put("省", "海南");
			user.getAddressMap().put("市", "三亚");
			session.save(user);
			
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
		
	}
	@Test
	public void testget() throws Exception {
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			User user = (User)session.get(User.class, 6);
			System.out.println(user.getName());
			System.out.println(user.getAddressSet());
			System.out.println(user.getAddressList());
			System.out.println(user.getAddressMap());
			
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
}
