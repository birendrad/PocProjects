package com.app.ems.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emp_id;
	private String emp_name;
	private String address;
	private String email;
	private long mobile;
	private double salary;
	

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long emp_id, String emp_name, String address, String email, long mobile, double salary) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.salary = salary;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", address=" + address + ", email=" + email
				+ ", mobile=" + mobile + ", salary=" + salary + "]";
	}

}
