package com.devops.bbm.bean;

public class PersonalInformation {
	private String name;
	private String DOB;
	private int Age;
	private String email;
	private String phone_number;
	private String bloodgroup;
	private String [] Medical_records;
	
	
	public PersonalInformation(String name, String dOB, int age, String email, String phone_number, String bloodgroup,
			String[] medical_records) {
		super();
		this.name = name;
		DOB = dOB;
		Age = age;
		this.email = email;
		this.phone_number = phone_number;
		this.bloodgroup = bloodgroup;
		Medical_records = medical_records;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		DOB = dOB;
	}


	public int getAge() {
		return Age;
	}


	public void setAge(int age) {
		Age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getBloodgroup() {
		return bloodgroup;
	}


	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}


	public String[] getMedical_records() {
		return Medical_records;
	}


	public void setMedical_records(String[] medical_records) {
		Medical_records = medical_records;
	}
	
	
}
