package com.workflowinsights.service;

import org.springframework.stereotype.Component;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.workflowinsights.dto.TaskDTO;

@Component
public class TaskService {
	
	/**
	 * Task DTO Building Service
	 * @param snapshot QueryDocumentSnapshot from Firestore
	 * @return TaskDTO Object with properties from Firestore
	 */
	public static TaskDTO initTask(QueryDocumentSnapshot snapshot) {

		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setId(snapshot.getId());
		taskDTO.setEstimatedHours(snapshot.getLong("estimatedHours").intValue());
		taskDTO.setDescription((String) snapshot.get("description"));
		taskDTO.setTaskname((String) snapshot.get("taskname"));
		taskDTO.setActualHours(snapshot.getLong("actualHours").intValue());

		return taskDTO;
		
	}

}
