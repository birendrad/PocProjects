package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Agent")
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long agentId;
	@Column(name = "agent_name")
	private String name;
	@Column(name = "agent_age",length = 3)
	private String age;
	@Column(name = "dept_name")
	private String deptName;
	@Column(name = "hub_id")
	private String hubId;
}
