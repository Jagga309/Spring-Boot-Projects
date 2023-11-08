package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/findAll")
	public List<Employee> getAllEmployees(
			@RequestParam(value = "pageNumber",defaultValue = "1" , required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "3",required = false) int pageSize)
	{
		return employeeService.getAllEmployees(pageNumber,pageSize);
	}
	
	@PostMapping("/add")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.addNewEmployee(employee);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		return employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
	}
}
