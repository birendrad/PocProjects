package com.app.service;

import java.util.List;

import com.app.entity.Hub;

public interface HubService {

	public Hub addNewHub(Hub hub);

	public List<Hub> listOfHubs();
	
	public Hub findById(long id);
}
