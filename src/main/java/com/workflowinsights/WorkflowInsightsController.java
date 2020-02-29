package com.workflowinsights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workflowinsights.dto.TaskDTO;
import com.workflowinsights.service.TaskServiceStub;

@Controller
public class WorkflowInsightsController {
	
	@Autowired
	private TaskServiceStub taskServiceStub;
	
	@RequestMapping(value="/createtask")
	public String createTask(TaskDTO taskDTO) {
		
		return"index";
		
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public String read(Model model) {
		TaskDTO taskDTO = taskServiceStub.fetchAllTasks();
		model.addAttribute("taskName",taskDTO.getTaskname());
		model.addAttribute("taskDescription",taskDTO.getDescription());
		model.addAttribute("taskEstimatedHours",taskDTO.getEstimatedHours());
		model.addAttribute("taskActualHours",taskDTO.getActualHours());



		return "index";
	}
	
	
	@RequestMapping(value="/newtask", method=RequestMethod.GET)
	public String read1(Model model) {
		model.addAttribute("taskDTO", new TaskDTO());
		return"newtask";
	}
}
