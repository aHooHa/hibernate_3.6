package com.sunyahui.l_second_cashe;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
	public void testSessionCashe() throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class, 1);
		tc.commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		Transaction tc1 = session1.beginTransaction();
		Employee employee1 = (Employee) session1.get(Employee.class, 1);
		tc1.commit();
		session1.close();

	}

	@Test
	public void testSecondCashe() throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getName());
		System.out.println(department.getEmployees());
		tc.commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		Transaction tc1 = session1.beginTransaction();
		Department department2 = (Department) session1.get(Department.class, 1);
		System.out.println(department2.getName());
		System.out.println(department2.getEmployees());
		tc1.commit();
		session1.close();

	}

	/**
	 * 当使用Query.list() 查询时，默认不会使用二级缓存。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQueryCashe() throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		List list = session.createQuery("from Employee where id<4").list();
		System.out.println(list);
		tc.commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		Transaction tc1 = session1.beginTransaction();
		List list1 = session1.createQuery("from Employee where id<4").list();
		System.out.println(list1);
		tc1.commit();
		session1.close();

	}

	/**
	 * 当使用HQL查询时，使用itertor,可以使用缓存
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQueryCashe2() throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		Iterator<Employee> iterator = session.createQuery("from Employee where id<4").iterate();
		while (iterator.hasNext()) {
			Employee e = iterator.next();
			System.out.println(e);
		}
		tc.commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		Transaction tc1 = session1.beginTransaction();
		Iterator<Employee> iterator1 = session1.createQuery("from Employee where id<4").iterate();
		while (iterator1.hasNext()) {
			Employee e = iterator1.next();
			System.out.println(e);
		}
		tc1.commit();
		session1.close();

	}

	@Test
	public void testQueryCashe3() throws Exception {

		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		List list = session.createQuery("from Employee where id<4")//
				.setCacheable(true)//需要在hibernate。cfg。xml中开启查询
				.list();

		System.out.println(list);
		tc.commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		Transaction tc1 = session1.beginTransaction();
		List list1 = session1.createQuery("from Employee where id<4")//
				.setCacheable(true)//
				.list();

		System.out.println(list1);
		tc1.commit();
		session1.close();

	}
}
