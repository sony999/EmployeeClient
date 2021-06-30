package com.nagarro.hrmanagement.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.hrmanagement.entities.Employee;
import com.nagarro.hrmanagement.services.EmployeeService;

@Controller
public class EditController {
	public EmployeeService service = new EmployeeService();
	@RequestMapping (value="/edit/{employeeCode}",method=RequestMethod.GET)
	public String edit(Model model, @PathVariable("employeeCode") long employeeCode) {
		 model.addAttribute("employee", new Employee());
		 Employee emp = service.getEmployeeById(employeeCode);
		 model.addAttribute("empData" , emp);
		 return "edit";
	}
	
	
	@RequestMapping (value="/update",method=RequestMethod.POST)
	public String update(Model model,@ModelAttribute("employee") Employee employee) {
		 model.addAttribute("employee", new Employee());
		 service.updateEmployee(employee);
		 return "redirect:/home";
	}
	
}
