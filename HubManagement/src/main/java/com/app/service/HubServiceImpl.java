package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Hub;
import com.app.repository.HubRepository;

@Service
public class HubServiceImpl implements HubService {

	@Autowired
	private HubRepository hubRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Hub addNewHub(Hub hub) {
		return hubRepository.save(hub);
	}

	@Override
	public List<Hub> listOfHubs() {
		return hubRepository.findAll();
	}

	@Override
	public Hub findById(long id) {
		return hubRepository.findById(id).get();
	}

}
