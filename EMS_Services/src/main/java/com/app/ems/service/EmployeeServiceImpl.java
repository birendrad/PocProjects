package com.app.ems.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.ems.entity.Employee;
import com.app.ems.entity.EmsFiles;
import com.app.ems.entity.KeyCloakUserEmplReg;
import com.app.ems.entity.UserDetailsRequest;
import com.app.ems.exceptions.MaxUploadLimitException;
import com.app.ems.repository.EmployeeRepo;
import com.app.ems.repository.EmsFilesRepo;
import com.app.ems.util.KeyCloakAdminUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private KeyCloakAdminUtil adminConfig;

	// add employee
	@Override
	public void addEmployee(UserDetailsRequest userDetailsRequest) {
		Employee employee = new Employee();
		employee.setEmp_id(userDetailsRequest.getEmp_id());
		employee.setEmp_name(userDetailsRequest.getEmp_name());
		employee.setAddress(userDetailsRequest.getAddress());
		employee.setEmail(userDetailsRequest.getEmail());
		employee.setMobile(userDetailsRequest.getMobile());
		employee.setSalary(userDetailsRequest.getSalary());
		KeyCloakUserEmplReg keyCloakUserEmplReg = new KeyCloakUserEmplReg();
		keyCloakUserEmplReg.setUserName(userDetailsRequest.getUserName());
		keyCloakUserEmplReg.setPassword(userDetailsRequest.getPassword());
		keyCloakUserEmplReg.setUserRole(userDetailsRequest.getUserRole());
		keyCloakUserEmplReg.setEmail(userDetailsRequest.getEmail());
		adminConfig.assignRoleToUser(keyCloakUserEmplReg);
		employeeRepo.save(employee);

	}

	// retrive all the employees
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		return employees;
	}

	// retrive employee by Id
	@Override
	public Employee getEmployeeById(long emp_id) {
		Optional<Employee> employee = employeeRepo.findById(emp_id);

		return employee.get();

	}

	
}
