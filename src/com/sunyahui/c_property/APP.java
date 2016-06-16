package com.sunyahui.c_property;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class APP {

	private static SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration()//
				.configure() // 读取指定的主配置文件
				.addClass(User.class)//
				.buildSessionFactory(); // 根据生成Session工厂
	}

	@Test
	public void testSave() throws Exception {
		//读取图片
		User user = new User();
		user.setName("玩吧");
		user.setAge(20);
		user.setTime(new Date());
		user.setDesci("shhjsdhfjahfjhjkshdfjdfsdfasdfa");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit(); // 提交事务
		session.close(); // 关闭Session，释放资源

	}

	@Test
	public void testGet() throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		User user = (User) session.get(User.class, 14); // 获取
		System.out.println(user);

		tx.commit();
		session.close();

	}

}
