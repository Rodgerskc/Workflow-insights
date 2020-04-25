package com.workflowinsights;

import java.io.IOException;
import java.util.ArrayList;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.workflowinsights.dto.TaskDTO;
import com.workflowinsights.service.TaskService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkflowinsightsApplication {

	public static Firestore db;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WorkflowinsightsApplication.class, args);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.getApplicationDefault())
				.setDatabaseUrl("https://workflow-insights.firebaseio.com").build();
	  
		FirebaseApp.initializeApp(options);
		db = FirestoreClient.getFirestore();
	}
	/**
	 * Adds task to Firestore
	 * @param task TaskDTO from Create Task form.
	 * @throws Exception
	 */
	public static void addTask(TaskDTO task) throws Exception {
		ApiFuture<WriteResult> future = db.collection("Tasks").document(task.getTaskname()).set(task);
		System.out.println("Successfully wrote to Firestore: " + future.get().getUpdateTime());
	}

	/**
	 * Deletes task from Firestore
	 * @param name TaskDTO from Delete Task form.
	 * @throws Exception
	 */
	public static void deleteTask(String name) throws Exception {
		ApiFuture<WriteResult> future = db.collection("Tasks").document(name.trim()).delete();
		System.out.println("Successfully deleted from Firestore: " + future.get().getUpdateTime());
	}

	/**
	 * Modifies task in Firestore
	 * @param id Task ID
	 * @param estimatedHours Task Estimated Hours
	 * @param description Task Description
	 * @throws Exception
	 */
	public static void modifyTask(String id, String estimatedHours, String description) throws Exception {
		System.out.println(id);
		ApiFuture<WriteResult> future = db.collection("Tasks").document(id).update("estimatedHours", estimatedHours,"description", description);
		System.out.println("Successfully deleted from Firestore: " + future.get().getUpdateTime());
	}

	/**
	 * Get all Tasks from Firestore
	 * @return ArrayList of TaskDTOs
	 * @throws Exception
	 */
	public static ArrayList<TaskDTO> getAllTasks() throws Exception {
		ApiFuture<QuerySnapshot> future = db.collection("Tasks").get();
		ArrayList<TaskDTO> tasks = new ArrayList<TaskDTO>();
		for (QueryDocumentSnapshot doc : future.get()) {
			tasks.add(TaskService.initTask(doc));
		}
		System.out.println(tasks);
		return tasks;
	}

}