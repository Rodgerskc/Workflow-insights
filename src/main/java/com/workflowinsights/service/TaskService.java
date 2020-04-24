package com.workflowinsights.service;

import org.springframework.stereotype.Component;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.workflowinsights.dto.TaskDTO;

@Component
public class TaskService {
	
	
	public static TaskDTO initTask(QueryDocumentSnapshot snapshot) {

		TaskDTO taskDTO = new TaskDTO();

		taskDTO.setTaskID(snapshot.getLong("taskID").intValue());
		taskDTO.setEstimatedHours(snapshot.getLong("estimatedHours").intValue());
		taskDTO.setDescription((String) snapshot.get("description"));
		taskDTO.setTaskname((String) snapshot.get("taskname"));
		taskDTO.setActualHours(snapshot.getLong("actualHours").intValue());

		return taskDTO;
		
	}

}
