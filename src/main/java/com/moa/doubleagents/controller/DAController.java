package com.moa.doubleagents.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moa.doubleagents.model.Agent;
import com.moa.doubleagents.model.DAModel;

@RestController
public class DAController {
	
	private static String AGENT_FILE = "src/main/resources/data/cc-maps-data-set.csv";
	
	@RequestMapping(
			value = "/loadAgents",
			method = RequestMethod.GET
			)
	public List<Agent> loadAgents() {
		return DAModel.readAgentData(AGENT_FILE);
	}
	
	@RequestMapping("/test")
	public String test() {
		return "This is a test.";
	}
}
