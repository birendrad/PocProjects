package com.app.ems.entity;

public class UserDetailsRequest {

	private long emp_id;
	private String emp_name;
	private String userName;
	private String password;
	private String address;
	private String email;
	private long mobile;
	private double salary;
	private String userRole;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UserDetailsRequest(long emp_id, String emp_name, String userName, String password, String address,
			String email, long mobile, double salary, String userRole) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.salary = salary;
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserDetailsRequest [emp_id=" + emp_id + ", emp_name=" + emp_name + ", userName=" + userName
				+ ", password=" + password + ", address=" + address + ", email=" + email + ", mobile=" + mobile
				+ ", salary=" + salary + ", userRole=" + userRole + "]";
	}
		
}
