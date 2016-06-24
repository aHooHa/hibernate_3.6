package com.sunyahui.i_one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	
	
	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Person.class)//
			.addClass(IdCard.class)//
			.buildSessionFactory();
	
	@Test
	public void testsave() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			
			Person person = new Person();
			person.setName("李华");

			IdCard idCard = new IdCard();
			idCard.setNumber1("3423435432431ew33");
			
			person.setIdCard(idCard);
			idCard.setPerson(person);
			
			session.save(person);
			session.save(idCard);
			
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
			Person person = (Person) session.get(Person.class, 1l);
			System.out.println(person);
			
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	@Test
	public void testRemoveRelation() throws Exception {
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			IdCard idCard = (IdCard) session.get(IdCard.class, 1l);
			idCard.setPerson(null);
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	@Test
	public void testDelete() throws Exception {
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			IdCard idCard = (IdCard) session.get(IdCard.class, 1l);
			session.delete(idCard);
			
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
}
