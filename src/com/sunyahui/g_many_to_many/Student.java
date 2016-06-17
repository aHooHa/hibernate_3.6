package com.sunyahui.g_many_to_many;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private long id;
	private String name;
	private Set<Teacher> teachers = new HashSet<>();
	
	
	public Student() {
		super();
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
