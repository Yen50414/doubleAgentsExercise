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
	
	public List<Agent> readData(String filename) {
		
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
			System.out.println("File " + filename + " not found.");
		} catch (Exception e) {
			System.out.println("An error occurred while reading data file.");
		}
		
		return agentList;
	}
}
