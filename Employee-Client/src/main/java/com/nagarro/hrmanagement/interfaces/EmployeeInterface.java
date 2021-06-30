package com.nagarro.hrmanagement.interfaces;

import java.util.List;

import com.nagarro.hrmanagement.entities.Employee;

public interface EmployeeInterface {
	List<Employee> getEmployees();
	Employee getEmployeeById(long id);
	Employee createEmployee(Employee employee);
	void deleteEmployee(long id);
	public Employee updateEmployee(Employee employee);
}

