package com.app.ems.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.ems.entity.Employee;
import com.app.ems.entity.UserDetailsRequest;
import com.app.ems.exceptions.MaxUploadLimitException;

public interface EmployeeService {

	void addEmployee(UserDetailsRequest userDetailsRequest);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long emp_id);
	
}
