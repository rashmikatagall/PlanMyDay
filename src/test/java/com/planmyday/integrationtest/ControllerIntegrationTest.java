package com.planmyday.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.planmyday.TaskDto.Task;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	private String url;
	
	@BeforeEach
	public void init() {
		url = "http://localhost:"+port;
	}
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void integrationTest1() {
		System.out.println("The port is : "+port);
		Task[] tasks = restTemplate.getForObject(url+"/PlanMyDay/tasks", Task[].class);
		
		assertThat(tasks.length == 2);
		
	}
	
	@Test
	public void integrationTest2() {
		ResponseEntity<Task> response  = restTemplate.getForEntity(url+"/PlanMyDay/tasks/1",Task.class);
		assertThat(response.getStatusCode() == HttpStatus.OK);
		System.out.println(response.getBody());
	}
	

}
