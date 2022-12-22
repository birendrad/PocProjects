package com.app.ems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsServicesApplication {
	
	private final Logger LOGGER = LoggerFactory.getLogger(EmsServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmsServicesApplication.class, args);
	}
}
