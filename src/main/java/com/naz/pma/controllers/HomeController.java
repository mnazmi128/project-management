package com.naz.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naz.pma.dao.EmployeeRepository;
import com.naz.pma.dao.ProjectRepository;
import com.naz.pma.dto.ChartData;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	ProjectRepository projRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("")
	public String index(Model model) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		
		List<ChartData> projectData = projRepo.projectStatusCount();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projects", projRepo.findAll());
		model.addAttribute("employeesProjectCount", empRepo.getEmployeeProjectCount());
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		return "main/home";
	}
	
}
