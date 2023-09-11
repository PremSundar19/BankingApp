package com.springboot.BankSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/updatenumber")
	public ResponseEntity<ResponseStructure<User>> updateNumber(@Valid @RequestParam int userId,
			@Valid @RequestParam long phone) {
		return service.updateNumber(userId, phone);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(@Valid @RequestParam String managerEmail,
			@Valid @RequestParam String managerPassword) {
		return service.getAllUsers(managerEmail, managerPassword);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@Valid @RequestParam int userId,
			@Valid @RequestParam int bankId) {
		return service.deleteUser(userId, bankId);
	}

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody User user,
			@Valid @RequestParam int bankId) {
		return service.saveUser(user, bankId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@Valid @RequestParam int userId) {
		return service.findUser(userId);
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user, @RequestParam int userId) {
		return service.updateUser(user, userId);
	}
}
