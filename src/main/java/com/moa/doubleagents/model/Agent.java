package com.moa.doubleagents.model;

/**
 * Class to represent an agent.  Contains information on an agent.
 * 
 * @author Arthur
 */
public class Agent {

	private String name;
	private double lat;
	private double lng;
	private int age;
	private String gender;

	public Agent(String name, double lat, double lng, int age, String gender) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.age = age;
		this.gender = gender;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getLatitude() {
		return this.lat;
	}
	
	public double getLongitude() {
		return this.lng;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Agent)) {
            return false;
        }

        Agent agent = (Agent) o;

        return agent.name.equals(name) &&
        		agent.lat == lat &&
        		agent.lng == lng &&
                agent.age == age &&
                agent.gender.equals(gender);
    }
	
	@Override
	public String toString() {
		return "{\"name\":\"" + name
				+ "\",\"age\":" + age
				+ ",\"gender\":\"" + gender
				+ "\",\"latitude\":" + lat
				+ ",\"longitude\":" + lng + "}";
	}
}
