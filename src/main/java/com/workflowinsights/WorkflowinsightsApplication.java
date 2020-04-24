package com.workflowinsights;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.WriteRequest;
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
		.setDatabaseUrl("https://workflow-insights.firebaseio.com")
		.build();
	  
		FirebaseApp.initializeApp(options);
		db = FirestoreClient.getFirestore();
	}

	public static void addTask(TaskDTO task) throws Exception {
		ApiFuture<WriteResult> future = db.collection("Tasks").document(task.getTaskname()).set(task);
		System.out.println("Successfully wrote to Firestore: " + future.get().getUpdateTime());
	}

	public static void deleteTask(String taskname) throws Exception {
		ApiFuture<WriteResult> future = db.collection("Tasks").document(taskname).delete();
		System.out.println("Successfully deleted from Firestore: " + future.get().getUpdateTime());
	}

	public static ArrayList<TaskDTO> getAllTasks() throws Exception {
		ApiFuture<QuerySnapshot> future = db.collection("Tasks").get();
		ArrayList<TaskDTO> tasks = new ArrayList<TaskDTO>();
		for (QueryDocumentSnapshot doc : future.get()) {
			tasks.add(TaskService.initTask(doc));
		}
		return tasks;
	}

}