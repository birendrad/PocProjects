package com.app.service;

import java.util.List;

import com.app.entity.Agent;

public interface AgentService {

	public Agent addNewAgent(Agent agent);
	
	public List<Agent> agentList();

	public List<Agent> findByHubId(long id);
	
}
