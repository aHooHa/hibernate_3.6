package com.sunyahui.l_hql_qbc;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Employee.class)//
			.addClass(Department.class)//
			.buildSessionFactory();

	@Test
	public void testsave() throws Exception {

		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Department department = new Department();
			department.setName("研发部");

			Employee employee1 = new Employee();
			Employee employee2 = new Employee();

			employee1.setName("张三");
			employee2.setName("李四");

			employee1.setDepartment(department);
			employee2.setDepartment(department);
			department.getEmployees().add(employee1);
			department.getEmployees().add(employee2);

			session.save(department);
			session.save(employee1);
			session.save(employee2);

			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Test
	public void testHql() throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.ge("id", 1));
			criteria.add(Restrictions.le("id", 2));
			criteria.addOrder(Order.desc("id"));
			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
