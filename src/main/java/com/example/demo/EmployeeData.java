package com.example.demo;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Employee")
public class EmployeeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String firstname;
	private String lastname;
	private String designation;
	private String salary;

	
	private String gender;
	private Date dob;
	private Boolean isPermanent;

	public EmployeeData() {
		
	}

	public EmployeeData(String id, String firstname,String lastname, String designation, String salary, String gender, Date hireDate,
			Boolean isPermanent) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname  = lastname;
		this.designation = designation;
		this.salary = salary;
		this.gender = gender;
		this.dob = hireDate;
		this.isPermanent = isPermanent;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Boolean getIsPermanent() {
		return isPermanent;
	}

	public void setIsPermanent(Boolean isPermanent) {
		this.isPermanent = isPermanent;
	}	
	
}
