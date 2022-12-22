package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

	public List<Agent> findByHubId(long id);
}
