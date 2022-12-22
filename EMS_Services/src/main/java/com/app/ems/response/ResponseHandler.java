package com.app.ems.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object respObject) {

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("message", message);
		responseMap.put("status", status.value());
		responseMap.put("data", respObject);
		return new ResponseEntity<Object>(responseMap, status);
	}
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("message", message);
		responseMap.put("status", status.value());
		return new ResponseEntity<Object>(responseMap, status);
	}

}
