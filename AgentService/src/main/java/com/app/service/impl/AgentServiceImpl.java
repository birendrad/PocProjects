package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Agent;
import com.app.repository.AgentRepository;
import com.app.service.AgentService;

import lombok.extern.java.Log;

@Service
@Log
public class AgentServiceImpl implements AgentService{

	@Autowired
	private AgentRepository agentRepository;
	
	@Override
	public Agent addNewAgent(Agent agent) {
		return agentRepository.save(agent);
	}

	@Override
	public List<Agent> agentList() {
		return agentRepository.findAll();
	}

	@Override
	public List<Agent> findByHubId(long id) {
		return agentRepository.findByHubId(id);
	}
}
