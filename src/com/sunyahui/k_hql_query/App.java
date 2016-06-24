package com.sunyahui.k_hql_query;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
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
	public void testHql() throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();

			String hql = null;
			// 简单查询,不可以使用select * from
			// hql = "FROM Employee ";
			// 带条件查询
			// hql ="from Employee where id<2";
			// 带有排序条件order by 查询
			// hql ="from Employee where id<2 order by id desc";
			// hql = "select e.name ,e.id from Employee e";
			// 带有new的查询
			hql = "select new Employee(e.id ,e.name) from Employee e";
			List list = session.createQuery(hql).list();
			for (Object object : list) {
				System.out.println(object);
			}
			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Test
	public void testHql1() throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();

			//String hql = null;
			// hql="select count(*) from Employee";
			// long num = (long) session.createQuery(hql).uniqueResult();
//			hql = "from Employee where id bewteen ? and ?";
//			List list = session.createQuery(hql)//
//					.setParameter(0, 1)//
//					.setParameter(1, 5)//
//					.list();
			//当集合参数时
//			hql="from Employee where id in(:ds)";
//			List list = session.createQuery(hql)//
//					.setParameterList("ds", new Object[]{1,2,3,4}).list();
			//使用命名查询
//			List  list =session.getNamedQuery("queryByIdRange")//
//					.setParameter("idMin", 2)//
//					.setParameter("idMax", 10)//
//					.list();
//			for (Object object : list) {
//				System.out.println(object);
//			}
			//update
			int result = session.createQuery(
					"update Employee e set e.name=? where id=1")//
					.setParameter(0, "dfjd")//
					.executeUpdate();
			System.out.println(result);
			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	@Test
	public void testHqlDDL() throws Exception {
		Session session = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, 1);
			System.out.println(employee);
			int result = session.createQuery(
					"update Employee e set e.name=? where id=1")//
					.setParameter(0, "dfjd")//
					.executeUpdate();
			session.refresh(employee);
			System.out.println(employee.getName());
			
			tc.commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
