package com.moa.doubleagents.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DAModelTest {
	
	private String testName = "Arthur Mo";
	private Double testLat = 40.00579;
	private Double testLng = -83.0278;
	private int testAge = 26;
	private String testGender = "Male";
	
	private DAModel model = new DAModel();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void readAgentDataTest() {
		List<Agent> actual = model.readAgentData("src/test/resources/data/moa-maps-data-set.csv");
		Agent expected = new Agent(testName, testLat, testLng, testAge, testGender);
		
		assertEquals("Error in data read.", expected, actual.get(0));
	}
	
	@Test
	public void read150AgentTest() {
		List<Agent> actual = model.readAgentData("src/main/resources/data/cc-maps-data-set.csv");
		assertEquals("Missing data, should have read 150 data sets.", 150, actual.size());
	}
	
	@Test
	public void readAgentDataMissingFieldTest() {
		//thrown.expect(NumberFormatException.class);
	    //thrown.expectMessage(CoreMatchers.containsString("For input string:"));
		
		model.readAgentData("src/test/resources/data/moa-maps-data-set-miss-field.csv");
	}
	
	@Test
	public void fileNotExistTest() {
		//thrown.expect(FileNotFoundException.class);
	    //thrown.expectMessage(CoreMatchers.containsString("The system cannot find the file specified"));
		
		model.readAgentData("src/test/resources/data/this-file-does-not-exist.csv");
	}

}
