package com.sunyahui.c_property;

import java.util.Date;

/**
 * 实体 User
 * 
 * @author SONY
 *
 */
public class User {
	private int id;
	private String name;
	private int age;
	private Date time;
	private String desci;
	

	public String getDesci() {
		return desci;
	}

	public void setDesci(String desci) {
		this.desci = desci;
	}

	

	public User() {
		super();
	}

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", time=" + time + "]";
	}

	

}
