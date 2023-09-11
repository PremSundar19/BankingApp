package com.springboot.BankSpringApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dao.ManagerDao;
import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.exception.ManagerEmailException;
import com.springboot.BankSpringApp.exception.ManagerNotFoundException;
import com.springboot.BankSpringApp.exception.ManagerPasswordException;
import com.springboot.BankSpringApp.repo.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	ManagerDao dao;

	@Autowired
	ManagerRepo repo;

	public ResponseEntity<ResponseStructure<Manager>> findManager(int managerId) {
		Manager exmanager = dao.findManager(managerId);
		ResponseStructure<Manager> st = new ResponseStructure<Manager>();
		if (exmanager != null) {
			st.setMessage("Manager found successfully");
			st.setStatus(HttpStatus.FOUND.value());
			st.setData(exmanager);
			return new ResponseEntity<ResponseStructure<Manager>>(st, HttpStatus.FOUND);
		}
		throw new ManagerNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager) {
		ResponseStructure<Manager> st = new ResponseStructure<Manager>();
		st.setMessage("Manager CREATED");
		st.setStatus(HttpStatus.CREATED.value());
		st.setData(dao.saveManager(manager));
		return new ResponseEntity<ResponseStructure<Manager>>(st, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Manager>> getByEmail(String managerEmail) {
		ResponseStructure<Manager> st = new ResponseStructure<Manager>();
		st.setMessage("Manager Permission");
		st.setStatus(HttpStatus.CREATED.value());
		st.setData(dao.getByEmail(managerEmail));
		return new ResponseEntity<ResponseStructure<Manager>>(st, HttpStatus.CREATED);
	}

	public Manager login(String email, String password) {
		Manager manager = repo.getByEmail(email);
		if (manager != null) {
			if (manager.getManagerPassword().equals(password)) {
				return manager;
			}
			throw new ManagerPasswordException();
		}
		throw new ManagerEmailException();
	}
}
