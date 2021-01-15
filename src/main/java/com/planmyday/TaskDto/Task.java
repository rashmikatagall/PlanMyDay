package com.planmyday.TaskDto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Task {
	
	private int id;
	private String taskName;
	private LocalDateTime date;
	private boolean isDone;
	
	public Task() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task(int id, String name, LocalDateTime date)
	{
		this.id  = id;
		this.taskName = name;
		this.date = date;
		this.isDone = false;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}
