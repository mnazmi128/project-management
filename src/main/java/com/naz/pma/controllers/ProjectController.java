package com.naz.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naz.pma.dao.EmployeeRepository;
import com.naz.pma.dao.ProjectRepository;
import com.naz.pma.entities.Employee;
import com.naz.pma.entities.Project;

@Controller
@RequestMapping("projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String projectForm(Model model) {
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", empRepo.findAll());
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model, @RequestParam List<Long> employees) {
		projectRepo.save(project);
		
		/*
		Iterable<Employee> assignedEmployees = empRepo.findAllById(employees);
		for(Employee employee : assignedEmployees) {
			employee.setProject(project);
			empRepo.save(employee);
		}
		*/
		
		return "redirect:/projects";
	}
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("projects", projectRepo.findAll());
		
		return "projects/index";
	}

}
