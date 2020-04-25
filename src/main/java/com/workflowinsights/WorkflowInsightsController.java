package com.workflowinsights;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import com.workflowinsights.dto.TaskDTO;

@Controller
public class WorkflowInsightsController {
	
	/**
	 * Default route
	 * @param model ModelView
	 * @return index.html
	 * @throws Exception
	 */
	@RequestMapping("/")
	public String index(Model model) throws Exception {
		ArrayList<TaskDTO> tasks = WorkflowinsightsApplication.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "index";
	}

	/**
	 * Tasks Route, for task CRUD operations
	 * @param model ModelView
	 * @return tasks.html
	 * @throws Exception
	 */
	@RequestMapping(value="/tasks", method=RequestMethod.GET)
	public String manageTasks(Model model) throws Exception {
		model.addAttribute("taskDTO", new TaskDTO());
		String deleteString = "";
		model.addAttribute(deleteString);
		ArrayList<TaskDTO> tasks = WorkflowinsightsApplication.getAllTasks();
		model.addAttribute("tasks", tasks);
		return"tasks";
	}
	
	/**
	 * Create task endpoint.
	 * @param taskDTO TaskDTO made from create form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/createtask")
	public String createTask(TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
		WorkflowinsightsApplication.addTask(taskDTO);
		}

		return"forward:/";
		
	}

	/**
	 * Modify task endpoint.
	 * @param id Task id
	 * @param description Task description
	 * @param estimatedhours Task estimatedHours
	 * @param taskDTO TaskDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifytask")
	public String modifyTask(@RequestParam String id, @RequestParam String description, @RequestParam String estimatedhours, TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
			WorkflowinsightsApplication.modifyTask(id, estimatedhours, description);
			}
	
		return"forward:/";
		
	}

	/**
	 * Delete task enpoint
	 * @param taskname Taskname for matching with Firebase record.
	 * @return
	 * @throws Exception
	 */
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
