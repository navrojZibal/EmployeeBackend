package com.example.demo;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "EmployeeRegisterTable")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	@Column(unique = true)
	private String userName;
	
	
	public Employee() {
		
	}


	public Employee(String id, String email, String password, String userName,String firstname,
	String lastname) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return userName;
	}


	public void setUsername(String userName) {
		this.userName = userName;
	}

	
}
