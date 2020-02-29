package com.workflowinsights.service;

import org.springframework.stereotype.Component;

import com.workflowinsights.dto.TaskDTO;

@Component
public class TaskServiceStub {
	
	
	public TaskDTO fetchAllTasks() {
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setTaskID(1);
		taskDTO.setEstimatedHours(2.5);
		taskDTO.setDescription("Complete programming assignment.");
		taskDTO.setTaskname("Assignment 1");
		taskDTO.setActualHours(0);
		return taskDTO;
		
	}

}
