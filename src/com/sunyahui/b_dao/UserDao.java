package com.sunyahui.b_dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunyahui.a_hello.User;


public class UserDao {

	public void save(User user) {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();
			session.save(user);
			tc.commit();
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}
	};

	public void update(User user) {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();
			session.update(user);
			tc.commit();
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}
	};

	public void delete(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			session.delete(user);
			tc.commit();
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}
	};

	public User getById(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			tc.commit();
			return user;
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}
	};

	public List<User> findAll() {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();

			List<User> lists = session.createQuery("FROM User").list();

			// Criteria criteria = session.createCriteria(User.class);
			// criteria.add(Restrictions.ge("id", 4));
			// criteria.add(Restrictions.le("id", 10));
			// criteria.addOrder(Order.desc("id"));
			// List<User> lists =criteria.list();
			tc.commit();
			return lists;
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}
	};

	public QueryResult findAll(int first, int maxstep) {
		Session session = HibernateUtil.openSession();
		Transaction tc = null;
		try {
			tc = session.beginTransaction();
			List<User> lists = session.createQuery("FROM User")//
					.setFirstResult(first)//
					.setMaxResults(maxstep)//
					.list();
			tc.commit();

			Long count = (Long) session.createQuery("SELECT COUNT(*) FROM User").uniqueResult();
			return new QueryResult(count.intValue(), lists);
		} catch (RuntimeException e) {
			tc.rollback();
			throw e;
		} finally {
			session.close();
		}

	};

}
