package com.workflowinsights.dto;

import com.google.cloud.firestore.QueryDocumentSnapshot;

public class TaskDTO {
	
	private String taskname;
	private String description;
	private double estimatedHours;
	private double actualHours;
	private String id;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public double getActualHours() {
		return actualHours;
	}
	public void setActualHours(double actualHours) {
		this.actualHours = actualHours;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return taskname + "\n" + description + "\n Estimated hours: " + estimatedHours + "\n Actual Hours: " + actualHours;
	}

}
