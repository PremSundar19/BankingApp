package com.springboot.BankSpringApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dto.Transaction;
import com.springboot.BankSpringApp.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	TransactionService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Transaction>> createTransaction(@Valid @RequestParam String uName,
			@Valid	@RequestParam String uPass,@Valid @RequestParam int transType,@Valid @RequestParam double transAmount) {

		return service.createTransaction(uName, uPass, transType, transAmount);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> findAll(@Valid @RequestParam String uName,
			@Valid @RequestParam String uPass) {
		return service.findAll(uName, uPass);
	}

	@GetMapping("/dates")
	public ResponseEntity<ResponseStructure<List<Transaction>>> betweenDates(@Valid @RequestParam String uName,
			@Valid @RequestParam String uPass,@Valid @RequestParam String d1,@Valid @RequestParam String d2) {
		return service.betweenDates(uName, uPass, LocalDate.parse(d1), LocalDate.parse(d2));
	}
}
