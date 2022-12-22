package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Agent;
import com.app.entity.Hub;
import com.app.service.HubServiceImpl;

@RestController
@RequestMapping("/hub")
public class HubController {

	@Autowired
	private HubServiceImpl hubServiceImpl;
	private List<Agent> agentsAssigned;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public ResponseEntity<String> isAppUp() {
		return ResponseEntity.status(HttpStatus.OK).body("Hub-Management Service App is up and deployed on 20/12/2022");
	}
	
	@PostMapping("/add")
	public ResponseEntity<Hub> addNewHub(@RequestBody Hub hub) {
		return ResponseEntity.status(HttpStatus.OK).body(hubServiceImpl.addNewHub(hub));
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Hub>> findListOfHub() {
		return ResponseEntity.status(HttpStatus.OK).body(hubServiceImpl.listOfHubs());
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public ResponseEntity<List<String>> findListOfAgentsInHub(@PathVariable Long id) {
		// get Hub details by id 
		
		Hub hub = hubServiceImpl.findById(id);
		agentsAssigned = restTemplate.getForObject("http://AGENT-SERVICE/agent/"+id, List.class);
		List<String> response = new ArrayList<>();
		response.add(hub.toString());
		response.add(agentsAssigned.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
