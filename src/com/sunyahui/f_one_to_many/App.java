package com.sunyahui.f_one_to_many;

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
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			Department department =new Department();
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
		}finally{
			session.close();
		}
		
		
		
	}
	@Test
	public void testget() throws Exception {
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			
			Department department = (Department) session.get(Department.class, 2);
			System.out.println(department);
			System.out.println(department.getEmployees());
			
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
//			Employee employee = (Employee) session.get(Employee.class, 7);
//			employee.setDepartment(null);
			//(注意 inverse 为false时才可以进行对外键操作)
			Department department = (Department) session.get(Department.class, 2);
			department.getEmployees().clear();
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
			//删除员工
//			Employee employee = (Employee) session.get(Employee.class, 7);
//			session.delete(employee);
			//删除部门
			Department department = (Department) session.get(Department.class, 2);
			session.delete(department);
			tc.commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
}
