package com.hr.person.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Person")
public class Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;
   
    private String firstName;
   
    private String lastName;
   
    private String email;
   
    private String status;
   
    private BigInteger phoneNumber;

    private String dob;

    private String address;
	
    
    
    
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(long id, String firstName, String lastName, String email, String status, BigInteger phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dob = dob;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
