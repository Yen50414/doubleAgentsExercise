package com.moa.doubleagents.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AgentTest {

	private String testName = "moa";
	private double testLat = 40.00579;
	private double testLng = -83.0278;
	private int testAge = 26;
	private String testGender = "Male";
	
	@Test
	public void testGetName() {
		Agent a = new Agent(testName, testLat, testLng, testAge, testGender);
		assertEquals("Agent returned the wrong name.", testName, a.getName());
	}
	
	@Test
	public void testGetLatitude() {
		Agent a = new Agent(testName, testLat, testLng, testAge, testGender);
		assertEquals("Agent returned the wrong latitude.", testLat, a.getLatitude(), 0.0);
	}
	
	@Test
	public void testGetLongitude() {
		Agent a = new Agent(testName, testLat, testLng, testAge, testGender);
		assertEquals("Agent returned the wrong longitude.", testLng, a.getLongitude(), 0.0);
	}
	
	@Test
	public void testGetAge() {
		Agent a = new Agent(testName, testLat, testLng, testAge, testGender);
		assertEquals("Agent returned the wrong age.", testAge, a.getAge());
	}
	
	@Test
	public void testGetGender() {
		Agent a = new Agent(testName, testLat, testLng, testAge, testGender);
		assertEquals("Agent returned the wrong gender.", testGender, a.getGender());
	}

}
