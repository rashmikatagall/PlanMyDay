package com.planmyday.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.planmyday.TaskDto.Task;
import com.planmyday.exceptions.TaskError;
import com.planmyday.exceptions.TaskNotFoundException;
import com.planmyday.service.TaskService;

@RestController
@RequestMapping("/PlanMyDay")
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getTasks() {
		List<Task> tasks = taskService.getTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable int id) {
		return new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK);

	}
	
}
