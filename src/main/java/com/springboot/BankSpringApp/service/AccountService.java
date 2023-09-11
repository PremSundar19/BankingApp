package com.springboot.BankSpringApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dao.AccountDao;
import com.springboot.BankSpringApp.dao.BankDao;
import com.springboot.BankSpringApp.dao.UserDao;
import com.springboot.BankSpringApp.dto.Account;
import com.springboot.BankSpringApp.dto.AccountType;
import com.springboot.BankSpringApp.dto.Bank;
import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.exception.AccountNotFoundException;
import com.springboot.BankSpringApp.exception.ManagerCredentialException;
import com.springboot.BankSpringApp.exception.UserNotFoundException;

@Service
public class AccountService {

	@Autowired
	AccountDao adao;
	@Autowired
	ManagerService mservice;

	@Autowired
	UserDao udao;

	@Autowired
	BankDao bdao;

	public ResponseEntity<ResponseStructure<Account>> savingToCurrent(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.SAVING) {
				exAcc.setAccountType(AccountType.CURRENT);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> savingToLoan(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.SAVING) {
				exAcc.setAccountType(AccountType.LOAN);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> currentToLoan(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.CURRENT) {
				exAcc.setAccountType(AccountType.LOAN);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> currentToSaving(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.CURRENT) {
				exAcc.setAccountType(AccountType.SAVING);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> loanToSaving(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.LOAN) {
				exAcc.setAccountType(AccountType.SAVING);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> loanToCurrent(int accId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null && exAcc.getAccountType() == AccountType.LOAN) {
				exAcc.setAccountType(AccountType.CURRENT);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.updateAccount(exAcc, accId));
				structure.setMessage("Account changed");
				structure.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}

	public ResponseEntity<ResponseStructure<Account>> saveAccount(Account account, String managerEmail,
			String managerPassword, int userId, int accType) {
		Manager exManager = mservice.login(managerEmail, managerPassword);
		User exUser = udao.findUser(userId);
		if (accType == 1) {
			account.setAccountType(AccountType.SAVING);
		} else if (accType == 2) {
			account.setAccountType(AccountType.CURRENT);
		} else if (accType == 3) {
			account.setAccountType(AccountType.LOAN);
		}
		if (exUser != null) {
			if (exManager != null) {
				account.setUser(exUser);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.saveAccount(account));
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setMessage("Account Created Successfully");
				exUser.setUserAccount(account);
				udao.updateUser(exUser, userId);
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.ACCEPTED);
			}
			throw new ManagerCredentialException();
		}
		throw new UserNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Account>> findAccount(int accountId) {
		Account exAccount = adao.findAccount(accountId);
		if (exAccount != null) {
			ResponseStructure<Account> structure = new ResponseStructure<Account>();
			structure.setData(exAccount);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Account is found Successfully");
			return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.FOUND);
		}
		throw new AccountNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Account>> deleteAccount(int accId, int bankId, String managerEmail,
			String managerPassword) {
		if (mservice.login(managerEmail, managerPassword) != null) {
			Account exAcc = adao.findAccount(accId);
			if (exAcc != null) {
				User exUser = exAcc.getUser();
				Bank exBank = bdao.findBank(bankId);
				List<User> exUsers = exBank.getUsers();
				exUsers.remove(exUser);
				exBank.setUsers(exUsers);
				bdao.updateBank(exBank, bankId);
				ResponseStructure<Account> structure = new ResponseStructure<Account>();
				structure.setData(adao.deleteAccount(accId));
				structure.setMessage("account deleted successfully");
				structure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.OK);
			}
			throw new AccountNotFoundException();
		}
		throw new ManagerCredentialException();
	}
}