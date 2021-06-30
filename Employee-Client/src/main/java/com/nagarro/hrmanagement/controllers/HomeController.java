package com.nagarro.hrmanagement.controllers;


import java.util.List;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nagarro.hrmanagement.entities.Employee;
import com.nagarro.hrmanagement.services.EmployeeService;
@Controller
public class HomeController {

	@RequestMapping(value="/home" , method=RequestMethod.GET) 
	public String login(Model model) {
		List<Employee> employees = new EmployeeService().getEmployees();
		model.addAttribute("employees" , employees);
		model.addAttribute("employee", new Employee());
		return "home";
	}
	
	@RequestMapping(value="/create" , method=RequestMethod.POST)
	public String create(@ModelAttribute("employee") Employee employee, Model model) {
		new EmployeeService().createEmployee(employee);
		return "redirect:/home";
	}
	
}
