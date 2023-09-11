package com.springboot.BankSpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dto.Account;
import com.springboot.BankSpringApp.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@Valid @RequestBody Account account,
			@Valid @RequestParam String managerEmail, @Valid @RequestParam String managerPassword,
			@Valid @RequestParam int userId, @Valid @RequestParam int accType) {
		return service.saveAccount(account, managerEmail, managerPassword, userId, accType);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Account>> findAccount(@Valid @RequestParam int accountid) {
		return service.findAccount(accountid);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(@Valid int accId, @Valid int bankId,
			String managerEmail, String managerPassword) {
		return service.deleteAccount(accId, bankId, managerEmail, managerPassword);
	}

	@PatchMapping("SavingToCurrent")
	public ResponseEntity<ResponseStructure<Account>> savingToCurrent(int accId, String managerEmail,
			String managerPassword) {
		return service.savingToCurrent(accId, managerEmail, managerPassword);
	}

	@PatchMapping("SavingToLoan")
	public ResponseEntity<ResponseStructure<Account>> savingToLoan(int accId, String managerEmail,
			String managerPassword) {
		return service.savingToLoan(accId, managerEmail, managerPassword);
	}

	@PatchMapping("CurrentToLoan")
	public ResponseEntity<ResponseStructure<Account>> currentToLoan(int accId, String managerEmail,
			String managerPassword) {
		return service.currentToLoan(accId, managerEmail, managerPassword);
	}

	@PatchMapping("CurrentToSaving")
	public ResponseEntity<ResponseStructure<Account>> currentToSaving(int accId, String managerEmail,
			String managerPassword) {
		return service.currentToSaving(accId, managerEmail, managerPassword);
	}

	@PatchMapping("LoanToSaving")
	public ResponseEntity<ResponseStructure<Account>> loanToSaving(int accId, String managerEmail,
			String managerPassword) {
		return service.loanToSaving(accId, managerEmail, managerPassword);
	}

	@PatchMapping("LoanToCurrent")
	public ResponseEntity<ResponseStructure<Account>> loanToCurrent(int accId, String managerEmail,
			String managerPassword) {
		return service.loanToCurrent(accId, managerEmail, managerPassword);
	}

}
