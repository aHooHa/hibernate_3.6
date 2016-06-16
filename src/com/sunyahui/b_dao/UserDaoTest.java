package com.sunyahui.b_dao;


import java.util.List;

import org.junit.Test;

import com.sunyahui.a_hello.User;


public class UserDaoTest {

	private UserDao dao = new UserDao();

	@Test
	public void testSave() {
		
		User user = new User();
		user.setName("赵六");
		dao.save(user);
		
	}

	@Test
	public void testUpdate() {
		User user = dao.getById(4);
		user.setName("张三");
		dao.update(user);
	}

	@Test
	public void testDelete() {
		dao.delete(2);
	}

	@Test
	public void testGetById() {
		User user = dao.getById(2);
		System.out.println(user);
	}

	@Test
	public void testFindAll() {
		List<User> lists =dao.findAll();
		for (User user : lists) {
			System.out.println(user);
		}
	
	}

	@Test
	public void testFindAllIntInt() {
		QueryResult res = dao.findAll(0, 4);
		System.out.println(res.getCount());
		List<User> lists =res.getList();
		for (User user : lists) {
			System.out.println(user);
		}
	
	}

}
