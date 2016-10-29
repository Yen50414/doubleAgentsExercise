package com.moa.doubleagents.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DAModelTest {
	
	private String testName = "Arthur Mo";
	private Double testLat = 40.00579;
	private Double testLng = -83.0278;
	private int testAge = 26;
	private String testGender = "Male";
	
	private DAModel model = new DAModel();

	@Test
	public void readDataTest() {
		List<Agent> actual = model.readData("src/test/resources/data/moa-maps-data-set.csv");
		Agent expected = new Agent(testName, testLat, testLng, testAge, testGender);
		
		assertEquals("Error in data read.", expected, actual.get(0));
	}
	
	@Test
	public void read150DataTest() {
		List<Agent> actual = model.readData("src/main/resources/data/cc-maps-data-set.csv");
		assertEquals("Missing data, should have read 150 data sets.", 150, actual.size());
	}

}
