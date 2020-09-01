package com.pgrsoft.springbatchlab.ejemplo08;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -41765676L;
	
	private String firstName;
	private String lastName;

	public Person() {
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
