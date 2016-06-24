package com.sunyahui.i_one_to_one;

/**
 * 身份证
 * 
 * @author SONY
 *
 */
public class IdCard {
	private long id;
	private String number1;
	private Person person;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber1() {
		return number1;
	}
	public void setNumber1(String number1) {
		this.number1 = number1;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", number1=" + number1 + "]";
	}

	
	

}
