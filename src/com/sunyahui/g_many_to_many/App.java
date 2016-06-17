package com.sunyahui.g_many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	
	
	private static SessionFactory sessionFactory = new Configuration()//
			.configure()//
			.addClass(Student.class)//
			.addClass(Teacher.class)//
			.buildSessionFactory();
	
	@Test
	public void testsave() throws Exception {
		
		Session session  = sessionFactory.openSession();
		try {
			Transaction tc = session.beginTransaction();
			
			Student student1 = new Student();
			student1.setName("孙同学"); 
			Student student2 = new Student();
			student2.setName("邢同学"); 

			Teacher teacher1 = new Teacher();
			teacher1.setName("万老师");
			
			Teacher teacher2 = new Teacher();
			teacher2.setName("张老师");
			
			
			student1.getTeachers().add(teacher1);
			student1.getTeachers().add(teacher2);
			
			student2.getTeachers().add(teacher1);
			student2.getTeachers().add(teacher2);
			
			teacher1.getStudents().add(student1);
			teacher1.getStudents().add(student2);
			
			teacher2.getStudents().add(student1);
			teacher2.getStudents().add(student2);

			session.save(student1);
			session.save(student2);
			session.save(teacher1);
			session.save(teacher2);
			
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
			Teacher teacher = (Teacher) session.get(Teacher.class, 2l);
            System.out.println(teacher);
            System.out.println(teacher.getStudents());
			
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
			Student student = (Student) session.get(Student.class, 1l);
			student.getTeachers().clear();
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
			Student student = (Student) session.get(Student.class, 2l);
			session.delete(student);
			tc.commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
}
