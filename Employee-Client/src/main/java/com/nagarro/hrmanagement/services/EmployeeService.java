package com.nagarro.hrmanagement.services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.hrmanagement.entities.Employee;
import com.nagarro.hrmanagement.interfaces.EmployeeInterface;

public class EmployeeService implements EmployeeInterface {
	
	public List<Employee> getEmployees() {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:8585/employee-api/employees"));
			HttpEntity entity = response.getEntity();
			String stringEmployees = EntityUtils.toString(entity);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(stringEmployees, new TypeReference<List<Employee>>() {});
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Employee getEmployeeById(long id) {
		String url = "http://localhost:8585/employee-api/employees/" + id;
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = client.execute(new HttpGet(url));
			HttpEntity entity = response.getEntity();
			String stringEmployee = EntityUtils.toString(entity);
			ObjectMapper mapper = new ObjectMapper();
			Employee employee = mapper.readValue(stringEmployee, Employee.class);
			return employee;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Employee createEmployee(Employee employee) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8585/employee-api/employees");
		
		JSONObject json = new JSONObject();
		json.put("employeeName", employee.getEmployeeName());
		json.put("location", employee.getLocation());
		json.put("email", employee.getEmail());
		json.put("dateOfBirth", employee.getDateOfBirth());
		
		StringWriter out = new StringWriter();
		try {
			json.writeJSONString(out);
			String jsonString = out.toString();
			StringEntity entity = new StringEntity(jsonString);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept" , "application/json");
			httpPost.setHeader("Content-type" , "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity1 = response.getEntity();
			String stringEmployee = EntityUtils.toString(entity1);
			System.out.print(stringEmployee);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee updateEmployee(Employee employee) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut("http://localhost:8585/employee-api/employees");
		
		JSONObject json = new JSONObject();
		json.put("employeeCode", employee.getEmployeeCode());
		json.put("employeeName", employee.getEmployeeName());
		json.put("location", employee.getLocation());
		json.put("email", employee.getEmail());
		json.put("dateOfBirth", employee.getDateOfBirth());
		
		StringWriter out = new StringWriter();
		try {
			json.writeJSONString(out);
			String jsonString = out.toString();
			StringEntity entity = new StringEntity(jsonString);
			httpPut.setEntity(entity);
			httpPut.setHeader("Accept" , "application/json");
			httpPut.setHeader("Content-type" , "application/json");
			CloseableHttpResponse response = client.execute(httpPut);
			HttpEntity entity1 = response.getEntity();
			String stringEmployee = EntityUtils.toString(entity1);
			System.out.print(stringEmployee);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
	}
}
