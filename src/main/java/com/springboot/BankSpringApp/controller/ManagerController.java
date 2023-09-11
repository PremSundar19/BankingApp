package com.springboot.BankSpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.service.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@Valid @RequestBody Manager manager) {
		return service.saveManager(manager);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Manager>> findManager(@Valid @RequestParam int managerId) {
		return service.findManager(managerId);
	}

	@GetMapping("/email")
	public ResponseEntity<ResponseStructure<Manager>> getByEmail(@Valid @RequestParam String managerEmail) {
		return service.getByEmail(managerEmail);
	}
}
