package com.moa.doubleagents;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.net.URL;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.moa.doubleagents.model.DAModel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DAControllerIT {
	
	private static String testFile = "src/main/resources/data/cc-maps-data-set.csv";
	
	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}
	
	@Test
	public void getLoadAgentsIT() throws Exception {
		String expected = DAModel.readAgentData(testFile).toString();//.replace('[', '{').replace(']', '}');
		JSONArray expectedJson = new JSONArray(expected);
		
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/loadAgents", String.class);
		JSONArray responseJson = new JSONArray(response.getBody());
		
		assertThat("Response from Controller should match the Model.", expectedJson.toString(), equalTo(responseJson.toString()));
	}
}
