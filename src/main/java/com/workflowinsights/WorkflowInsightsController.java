package com.workflowinsights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import com.workflowinsights.dto.TaskDTO;
import com.workflowinsights.service.TaskService;

@Controller
public class WorkflowInsightsController {
	
	@Autowired
	private TaskService taskServiceStub;
	
	@RequestMapping("/")
	public String index(Model model) throws Exception {
		ArrayList<TaskDTO> tasks = WorkflowinsightsApplication.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "index";
	}

	@RequestMapping(value="/tasks", method=RequestMethod.GET)
	public String manageTasks(Model model) throws Exception {
		model.addAttribute("taskDTO", new TaskDTO());
		String deleteString = "";
		model.addAttribute(deleteString);
		ArrayList<TaskDTO> tasks = WorkflowinsightsApplication.getAllTasks();
		model.addAttribute("tasks", tasks);
		return"tasks";
	}
	
	@RequestMapping(value="/createtask")
	public String createTask(TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
		WorkflowinsightsApplication.addTask(taskDTO);
		}

		return"forward:/";
		
	}

	@RequestMapping(value="/modifytask")
	public String modifyTask(@RequestParam String id, @RequestParam String description, @RequestParam String estimatedhours, TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
			WorkflowinsightsApplication.modifyTask(id, estimatedhours, description);
			}
	
		return"forward:/";
		
	}

	@RequestMapping(value="/deletetask")
	public String deleteTask(@RequestParam String taskname) throws Exception {
		String[] tasksplit = taskname.split("\n");
		String name = tasksplit[0];
		System.out.println("Delete DTO name " + name);
		if (taskname != null){
			WorkflowinsightsApplication.deleteTask(name);
			}
		return"forward:/";
		
	}
}
