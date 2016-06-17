package com.sunyahui.h_session;

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
	
			Transaction tc = session.beginTransaction();
			
			User user = new User();//临时状态
			user.setName("李华");
			session.save(user);//持久化状态
			
			tc.commit();
			session.close();
			user.setName("李四"); //游离
			System.out.println(user.getName());
	}
	@Test
	public void testload() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			User user = (User) session.load(User.class, 2);
			System.out.println(user.getClass());
			System.out.println(user);
			
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
		
	}
	@Test
	public void testsave_update() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			//id 确保数据库中有。不然会报错。
			User user = new User();
			user.setId(2);
			user.setName("张三");
			session.saveOrUpdate(user);
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
		
	}
	@Test
	public void testdelete() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			//User user = (User) session.get(User.class, 1);
			
			User user = new User();
			user.setId(2);
			session.delete(user);
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
		
		
		
	}
	@Test
	public void testbatchsave() throws Exception {
		
		Session session  = sessionFactory.openSession();
	
			Transaction tc = session.beginTransaction();
			
			for(int i=0;i<10000;i++){
				User user = new User();
				user.setName("lijsh");
				session.save(user);
				
				if(i%100==0){
					session.flush();
					session.clear();
				}
			}
			
			tc.commit();
			session.close();
		
	}
}
