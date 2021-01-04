package com.naz.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naz.pma.dao.EmployeeRepository;
import com.naz.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String createEmployee(Model model) {
		Employee newEmployee = new Employee();
		model.addAttribute("employee", newEmployee);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String saveEmployee(Employee employee, Model model) {
		empRepo.save(employee);
		
		return "redirect:/employees/new";
	}
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("employees",empRepo.findAll());
		
		return "employees/index";
	}
}
