package com.planmyday.controlleradvisor;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.planmyday.exceptions.TaskError;
import com.planmyday.exceptions.TaskNotFoundException;

@ControllerAdvice
public class ControllerAdvisor{// extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<TaskError> handleTaskNotFoundException(TaskNotFoundException te) {
		
		TaskError error = new TaskError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), te.getMessage());
		return new  ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<TaskError> handleTaskNotFoundException(MethodArgumentTypeMismatchException e) {
		
		TaskError error = new TaskError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),e.getMessage());
		return new  ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}

}
