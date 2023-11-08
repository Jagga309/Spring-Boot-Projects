package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.entities.Employee;
import com.app.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	public Employee addNewEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> getAllEmployees(int pageNumber,int pageSize) {
//		return employeeRepo.findAll();
		
//		int pageSize = 3;
//		int pageNumber = 1;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Employee> pagePost = this.employeeRepo.findAll(pageable);
		List<Employee> content = pagePost.getContent();
		return content;
	}
	
	public Employee getEmployeeById(Long id) {
		Optional<Employee> empOptional = employeeRepo.findById(id);
		if(empOptional.isPresent()) return empOptional.get();
		else return null;
	}
	
	public Employee updateEmployee(Long id,Employee employee) {
		
		Employee updatedEmployee = employeeRepo.findById(id).get();
		
		updatedEmployee.setName(employee.getName());
		updatedEmployee.setEmail(employee.getEmail());
		updatedEmployee.setPhone(employee.getPhone());
		updatedEmployee.setCreatedBy(employee.getCreatedBy());
		updatedEmployee.setCreatedOn(employee.getCreatedOn());
		return employeeRepo.save(updatedEmployee);
		
	}
	
	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);
	}
}
