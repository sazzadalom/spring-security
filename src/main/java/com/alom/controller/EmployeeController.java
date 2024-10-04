package com.alom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alom.bean.Employee;
import com.alom.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/get-all")
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployee());
	}
	
	@GetMapping("/get-session-id")
	public ResponseEntity<String> getSessionId(HttpServletRequest request) {
		String sessionId = "Session id: " + request.getSession().getId();
		return ResponseEntity.ok(sessionId);
	}
	
	@GetMapping("/get-csrf-token")
	public ResponseEntity<CsrfToken> getCSRFToken(HttpServletRequest request) {
		CsrfToken csrfToken =(CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return ResponseEntity.ok(csrfToken);
	}

}
