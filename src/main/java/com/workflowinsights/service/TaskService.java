package com.workflowinsights.service;

import org.springframework.stereotype.Component;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.workflowinsights.dto.TaskDTO;

@Component
public class TaskService {
	
	
	public TaskDTO initTask(QueryDocumentSnapshot snapshot) {
		TaskDTO taskDTO = new TaskDTO();
		
		//taskDTO.setTaskID(snapshot.get("taskID"));
		taskDTO.setEstimatedHours(2.5);
		taskDTO.setDescription("Complete programming assignment.");
		taskDTO.setTaskname("Assignment 1");
		taskDTO.setActualHours(0);
		return taskDTO;
		
	}

}
