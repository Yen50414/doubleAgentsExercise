package com.moa.doubleagents.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DAModel {
	
	private List<Agent> agentList;
	
	public List<Agent> loadAgentData() {
		//agentList = readAgentData("src/main/resources/data/cc-maps-data-set.csv");
		return readAgentData("src/test/resources/data/moa-maps-data-set.csv");
	}
	
	public List<Agent> readAgentData(String filename) {
		
		List<Agent> agentList = new ArrayList<Agent>();
		
		try (Reader in = new FileReader(filename)){
			
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				agentList.add(new Agent(
						record.get(0),
						Double.parseDouble(record.get(1)),
						Double.parseDouble(record.get(2)),
						Integer.parseInt(record.get(3)),
						record.get(4)
						));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error opening Agent data file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading Agent data file.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Error converting Agent data from String to number.");
			e.printStackTrace();
		}
		
		return agentList;
	}
}
