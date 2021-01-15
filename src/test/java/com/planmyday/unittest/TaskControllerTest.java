package com.planmyday.unittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.TypeRef;
import com.planmyday.TaskDto.Task;
import com.planmyday.controller.TaskController;
import com.planmyday.exceptions.TaskNotFoundException;
import com.planmyday.service.TaskService;

@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TaskService taskService;

	@Autowired
	TaskController taskController;

	List<Task> tasks;

	@BeforeEach
	public void init() {
		Task t1 = new Task(1, "PMD api", LocalDateTime.now());
		Task t2 = new Task(2, "Spring boot", LocalDateTime.now());
		tasks = Arrays.asList(t1, t2);
	}

	@Test
	public void testGetTasks() throws Exception{
		Mockito.when(taskService.getTasks()).thenReturn(tasks);
		MvcResult result = mockMvc.perform(get("/PlanMyDay/tasks"))
				                  .andExpect(status().isOk())
				                  .andReturn();
        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        
        List<Task> tasks = objectMapper.readValue(json,new TypeReference<List<Task>>() {});
        assertThat(tasks.size()==2);
	}
	

	@Test
	public void testGetTask1() throws Exception{
		Mockito.when(taskService.getTaskById(anyInt())).thenReturn(tasks.get(0));
		MvcResult result = mockMvc.perform(get("/PlanMyDay/tasks/1"))
				                  .andExpect(status().isOk())
				                  .andReturn();
        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        
        Task task = objectMapper.readValue(json, Task.class);
        assertEquals(task.getTaskName(),tasks.get(0).getTaskName());
	}
	
	@Test
	public void testGetTask_invalidId() throws Exception{
		Mockito.when(taskService.getTaskById(anyInt())).thenThrow(TaskNotFoundException.class);
		mockMvc.perform(get("/PlanMyDay/tasks/200"))
			   .andExpect(status().isNotFound())
			   .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Not Found")));
			   
	}
	
	
	
	
	}
	