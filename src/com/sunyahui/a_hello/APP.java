package com.sunyahui.a_hello;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class APP {
	
	
	private static SessionFactory sessionFactory;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml"); // 读取指定的主配置文件
		sessionFactory = cfg.buildSessionFactory(); // 根据生成Session工厂
	}

	@Test
	public void testSave() throws Exception{
		User user = new User();
		user.setName("王五");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit(); // 提交事务
		session.close(); // 关闭Session，释放资源
		
	}
	@Test
	public void testGet() throws Exception{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user = (User) session.get(User.class, 4); // 获取
		System.out.println(user);

		tx.commit();
		session.close();
		
	}
}
