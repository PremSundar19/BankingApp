package com.springboot.BankSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dto.Bank;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.service.BankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService service;

	@PatchMapping
	public ResponseEntity<ResponseStructure<Bank>> addManagerToBank(@Valid int managerid,@Valid int bankid) {
		return service.addManagerToBank(managerid, bankid);
	}

	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@Valid @RequestBody Bank bank) {
		return service.saveBank(bank);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Bank>> findBank(@Valid @RequestParam int bankid) {
		return service.findBank(bankid);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<List<User>>> findAll(@Valid @RequestParam String manageremail,
			@Valid	@RequestParam String managerpassword) {
		return service.findAllUser(manageremail, managerpassword);
	}
}
