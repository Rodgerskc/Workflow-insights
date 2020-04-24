package com.workflowinsights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workflowinsights.dto.TaskDTO;
import com.workflowinsights.service.TaskService;

@Controller
public class WorkflowInsightsController {
	
	@Autowired
	private TaskService taskServiceStub;
	
	@RequestMapping(value="/createtask")
	public String createTask(TaskDTO taskDTO) throws Exception {

		if (taskDTO.getTaskname() != null){
		WorkflowinsightsApplication.addTask(taskDTO);
		}

		return"index";
		
	}
	
	@RequestMapping("/")
	public String index() throws Exception {
		WorkflowinsightsApplication.getAllTasks();
		return "index";
	}
	
	
	@RequestMapping(value="/newtask", method=RequestMethod.GET)
	public String read1(Model model) {
		model.addAttribute("taskDTO", new TaskDTO());
		return"newtask";
	}
}
