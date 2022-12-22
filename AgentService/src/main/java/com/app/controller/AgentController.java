package com.app.controller;

import java.util.List;

import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Agent;
import com.app.service.impl.AgentServiceImpl;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentServiceImpl agntServiceImpl;

	@GetMapping("/")
	public ResponseEntity<String> isAppUp() {
		return ResponseEntity.status(HttpStatus.OK).body("Agen-Service App is up and deployed on 20/12/2022");
	}

	@PostMapping("/add")
	public ResponseEntity<Agent> addNewAgent(@RequestBody Agent agent) {
		Agent agentResp = agntServiceImpl.addNewAgent(agent);
		return ResponseEntity.status(HttpStatus.OK).body(agentResp);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Agent>> findListOfAgents() {
		return ResponseEntity.status(HttpStatus.OK).body(agntServiceImpl.agentList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Agent>> findListOfAgentsByHubIds(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK).body(agntServiceImpl.findByHubId(id));
	}

}
