package com.springboot.BankSpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dao.BankDao;
import com.springboot.BankSpringApp.dao.ManagerDao;
import com.springboot.BankSpringApp.dao.UserDao;
import com.springboot.BankSpringApp.dto.Bank;
import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.exception.BankNotFoundException;
import com.springboot.BankSpringApp.exception.ManagerCredentialException;
import com.springboot.BankSpringApp.exception.ManagerNotFoundException;

@Service
public class BankService {
	@Autowired
	BankDao dao;

	@Autowired
	ManagerService service;

	@Autowired
	UserDao udao;

	@Autowired
	ManagerDao mdao;

	public ResponseEntity<ResponseStructure<Bank>> addManagerToBank(int managerid, int bankid) {
		Bank exbank = dao.findBank(bankid);
		Manager exmanager = mdao.findManager(managerid);
		if (exmanager != null) {
			exbank.setBankManager(exmanager);
			ResponseStructure<Bank> structure = new ResponseStructure<Bank>();
			structure.setData(dao.updateBank(exbank, bankid));
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Manager Added to bank");
			return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.CREATED);
		}
		throw new ManagerNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank bank) {
		ResponseStructure<Bank> structure = new ResponseStructure<Bank>();
		structure.setMessage("Bank Added Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveBank(bank));
		return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Bank>> findBank(int bankid) {
		Bank exBank = dao.findBank(bankid);
		if (exBank != null) {
			ResponseStructure<Bank> structure = new ResponseStructure<Bank>();
			structure.setMessage("Bank Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exBank);
			return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.FOUND);
		}
		throw new BankNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllUser(String manageremail, String managerpassword) {
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
		if (service.login(manageremail, managerpassword) != null) {
			structure.setData(udao.findAll());
			structure.setMessage("All User Found");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		throw new ManagerCredentialException();
	}
}