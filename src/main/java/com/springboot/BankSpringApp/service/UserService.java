package com.springboot.BankSpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dao.BankDao;
import com.springboot.BankSpringApp.dao.UserDao;
import com.springboot.BankSpringApp.dto.Bank;
import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.exception.BankNotFoundException;
import com.springboot.BankSpringApp.exception.ManagerCredentialException;
import com.springboot.BankSpringApp.exception.UserNotFoundException;
import com.springboot.BankSpringApp.exception.UserPasswordException;
import com.springboot.BankSpringApp.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	UserDao udao;

	@Autowired
	ManagerService service;

	@Autowired
	UserRepo repo;

	@Autowired
	BankDao bdao;

	public ResponseEntity<ResponseStructure<User>> updateNumber(int userId, long phone) {
		User exUser = udao.findUser(userId);
		if (exUser != null) {
			exUser.setUserContact(phone);
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(udao.updateUser(exUser, userId));
			structure.setMessage("phone updated successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		throw new UserNotFoundException();
	}

	public User loginUser(String email, String password) {
		User loginUser = repo.loginUser(email);
		if (loginUser != null) {
			if (loginUser.getUserPassword().equals(password)) {
				return loginUser;
			}
			throw new UserPasswordException();
		}
		throw new UserNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int userId) {
		User exuser = udao.findUser(userId);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if (exuser != null) {
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(udao.updateUser(user, userId));
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(String managerEmail, String managerPassword) {
		Manager exManager = service.login(managerEmail, managerPassword);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		if (exManager != null) {
			structure.setMessage("All Users Founded");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(udao.findAll());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.FOUND);
		}
		throw new ManagerCredentialException();

	}

	public ResponseEntity<ResponseStructure<User>> saveUser(User user, int bankId) {
		Bank exBank = bdao.findBank(bankId);
		if (exBank != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setMessage("user saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(udao.saveUser(user));
			List<User> exUsers = exBank.getUsers();
			exUsers.add(user);
			bdao.updateBank(exBank, bankId);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		throw new BankNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findUser(int userId) {
		User exuser = udao.findUser(userId);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if (exuser != null) {
			structure.setMessage("user Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exuser);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId, int bankId) {
		User exuser = udao.findUser(userId);
		Bank exBank = bdao.findBank(bankId);
		if (exBank != null) {
			List<User> users = exBank.getUsers();
			users.remove(exuser);
			exBank.setUsers(users);
			bdao.updateBank(exBank, bankId);
			if (exuser != null) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setMessage("user Deleted successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(udao.deleteUser(userId));
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
			}
			throw new UserNotFoundException();
		}
		throw new BankNotFoundException();
	}
}