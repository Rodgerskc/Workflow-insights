package com.workflowinsights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(value="/newtask", method=RequestMethod.GET)
	public String read1(Model model) {
		model.addAttribute("taskDTO", new TaskDTO());
		return"newtask";
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
	public String modifyTask(TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
		WorkflowinsightsApplication.addTask(taskDTO);
		}

		return"forward:/";
		
	}

	@RequestMapping(value="/deletetask")
	public String deleteTask(String taskname) throws Exception {
		System.out.println("Delete DTO name" + taskname);
		if (taskname != null){
			WorkflowinsightsApplication.deleteTask(taskname);
			}
		return"forward:/";
		
	}
}
