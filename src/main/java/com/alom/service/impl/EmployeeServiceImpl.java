package com.alom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alom.bean.Employee;
import com.alom.repository.EmployeeRepository;
import com.alom.service.EmployeeService;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository; 
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	
}
