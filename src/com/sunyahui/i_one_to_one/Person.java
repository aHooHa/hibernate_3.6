package com.sunyahui.i_one_to_one;

/**
 * 公民
 * 
 * @author SONY
 *
 */
public class Person {
	private long id;
	private String name;
	private IdCard idCard;

	public Person() {
		super();
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

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", idCard=" + idCard + "]";
	}

}
