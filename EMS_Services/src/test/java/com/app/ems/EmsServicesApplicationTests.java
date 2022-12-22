package com.app.ems;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.ems.entity.Employee;
import com.app.ems.entity.KeyCloakUserEmplReg;
import com.app.ems.entity.UserDetailsRequest;
import com.app.ems.repository.EmployeeRepo;
import com.app.ems.service.EmployeeServiceImpl;
import com.app.ems.util.KeyCloakAdminUtil;
import com.fasterxml.jackson.annotation.JsonCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmsServicesApplicationTests {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@MockBean
	private EmployeeRepo emplRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testAddEmployees() {
		UserDetailsRequest userDetailsRequest = new UserDetailsRequest(1, "raj", "test", "test", "india", "raj@neosoft.com", 7894561230L, 100000d, "employee");
		KeyCloakAdminUtil adminUtils = mock(KeyCloakAdminUtil.class);
		EmployeeServiceImpl detailsRequest = mock(EmployeeServiceImpl.class);
		doNothing().when(adminUtils).assignRoleToUser(new KeyCloakUserEmplReg());
		doNothing().when(detailsRequest).addEmployee(userDetailsRequest);
		detailsRequest.addEmployee(userDetailsRequest);
		verify(detailsRequest, times(1)).addEmployee(userDetailsRequest);
	}

	@Test
	public void testFindAllEmployees() {
		when(emplRepo.findAll()).thenReturn(Stream
				.of(new Employee(1, "raj", "india", "raj@neosoft.com", 7894561230L, 100000d),
						new Employee(2, "swraj", "india", "swraj@neosoft.com", 7806123000L, 100000d))
				.collect(Collectors.toList()));
		assertEquals(2, employeeService.getAllEmployees().size());
	}

	@Test
	public void testFindEmployeeById() {
		long id = 1;
		Employee empl1 = new Employee(1, "raj", "india", "raj@neosoft.com", 7894561230L, 100000d);
		when(emplRepo.findById(id))
				.thenReturn(Optional.of(empl1));
		assertEquals(1, employeeService.getEmployeeById(id).getEmp_id());
	}
}
