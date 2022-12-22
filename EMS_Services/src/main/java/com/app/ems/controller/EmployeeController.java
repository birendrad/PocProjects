package com.app.ems.controller;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.ems.entity.Employee;
import com.app.ems.entity.UserDetailsRequest;
import com.app.ems.exceptions.MaxUploadLimitException;
import com.app.ems.response.FileResponse;
import com.app.ems.response.ResponseHandler;
import com.app.ems.service.EmployeeServiceImpl;
import com.app.ems.service.FileUploadService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Value("${project.image}")
	private String path;

	@Autowired
	private EmployeeServiceImpl employeeService;

	@Autowired
	private FileUploadService fileUploadService;

	@RolesAllowed("admin")
	@PostMapping("/add")
	public ResponseEntity<Object> addEmployee(@RequestHeader("Authorization") String authHeader,
			@RequestBody UserDetailsRequest userDetailsRequest) throws Exception {

		LOGGER.info("start addEmployee", userDetailsRequest.toString());
		employeeService.addEmployee(userDetailsRequest);
		return ResponseHandler.generateResponse("Employee added sucessfully", HttpStatus.OK);

	}

	@RolesAllowed({ "admin" })
	@GetMapping("/findAll")
	public ResponseEntity<Object> getAllEmployees() {

		LOGGER.info("start getAllEmployees");
		try {
			List<Employee> employees = employeeService.getAllEmployees();
			return ResponseHandler.generateResponse(" All Employee Details fetched sucessfully", HttpStatus.OK,
					employees);
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Please contact admin and report", HttpStatus.BAD_REQUEST,
					e.getMessage());
		}
	}

	@GetMapping("/{emp_id}")
	@RolesAllowed({ "employee", "admin" })
	public ResponseEntity<Object> getEmployeeById(@PathVariable int emp_id) {

		LOGGER.info("start getEmployeeById");
		Employee employee = employeeService.getEmployeeById(emp_id);
		return ResponseHandler.generateResponse("Employee Found sucessfully", HttpStatus.OK, employee);

	}

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile[] image)
			throws MaxUploadLimitException {

		LOGGER.info("start fileUpload");
		try {
			fileUploadService.uploadImage(path, image);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<FileResponse>(new FileResponse("", "Image is not Uploaded"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<FileResponse>(new FileResponse("", "Image Sucessfully Uploaded"), HttpStatus.OK);

	}

	@GetMapping("/image/{fileName}")
	public ResponseEntity<?> fileDownload(@PathVariable String fileName) throws IOException, DataFormatException {
		LOGGER.info("start fileDownload");
		byte[] image = fileUploadService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);

	}

}
