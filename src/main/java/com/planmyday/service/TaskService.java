package com.planmyday.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.planmyday.TaskDto.Task;
import com.planmyday.exceptions.TaskNotFoundException;

@Service
public class TaskService {

	Task t1 = new Task(1,"PMD api",LocalDateTime.now());
	Task t2 = new Task(2, "Spring boot", LocalDateTime.now());
	List<Task> tasks = Arrays.asList(t1,t2);
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public Task getTaskById(int id) {
		
         Optional<Task> optional = tasks.stream().filter(t -> t.getId()==id).findFirst();
         if(optional.isPresent()) 
        	 return optional.get();
         else
        	 throw new TaskNotFoundException(id);
         
		
	}
}
