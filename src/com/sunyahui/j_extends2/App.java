package com.sunyahui.j_extends2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Article.class)//
			.buildSessionFactory();

	@Test
	public void testsave() throws Exception {

		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Article a = new Article();
			a.setTitle("article");
			
			Topic t = new Topic();
			t.setTitle("article");
			
			Reply r = new Reply();
			r.setTitle("article");
			
			session.save(a);
			session.save(t);
			session.save(r);

			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Test
	public void testget() throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Article ar = (Article) session.get(Article.class, 1);
			System.out.println(ar);
			
			Topic pt = (Topic) session.get(Topic.class, 2);
			System.out.println(pt);
			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}


}
