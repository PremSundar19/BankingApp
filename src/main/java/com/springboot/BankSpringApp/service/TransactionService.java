package com.springboot.BankSpringApp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.BankSpringApp.config.ResponseStructure;
import com.springboot.BankSpringApp.dao.AccountDao;
import com.springboot.BankSpringApp.dao.TransactionDao;
import com.springboot.BankSpringApp.dao.UserDao;
import com.springboot.BankSpringApp.dto.Account;
import com.springboot.BankSpringApp.dto.Transaction;
import com.springboot.BankSpringApp.dto.TransactionType;
import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.exception.AccountNotFoundException;
import com.springboot.BankSpringApp.exception.InsufficientBalanceException;
import com.springboot.BankSpringApp.exception.TransactionTypeException;
import com.springboot.BankSpringApp.exception.UserCredentialException;
import com.springboot.BankSpringApp.repo.TransactionRepo;

@Service
public class TransactionService {
	@Autowired
	TransactionDao tdao;
	@Autowired
	UserDao udao;
	@Autowired
	AccountDao adao;
	@Autowired
	TransactionRepo trepo;

	public ResponseEntity<ResponseStructure<List<Transaction>>> betweenDates(String uName, String uPass, LocalDate d1,
			LocalDate d2) {
		if (udao.loginUser(uName, uPass) != null) {
			ResponseStructure<List<Transaction>> structure = new ResponseStructure<>();
			structure.setData(trepo.betweenDates(d1, d2));
			structure.setMessage("Transaction found between dates");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.FOUND);
		}
		throw new UserCredentialException();
	}

	public ResponseEntity<ResponseStructure<Transaction>> createTransaction(String uName, String uPass, int transType,
			double transAmount) {
		User exUser = udao.loginUser(uName, uPass);
		if (exUser != null) {
			Account exAcc = exUser.getUserAccount();
			if (exAcc != null) {
				if (transType == 1) {
					Transaction t = new Transaction();
					t.setStatus(TransactionType.CREDITED);
					t.setTransactionDate(LocalDate.now());
					t.setTransactionAmount(transAmount);
					tdao.saveTransaction(t);
					List<Transaction> exTrans = exAcc.getAccountTransaction();
					exTrans.add(t);
					exAcc.setAccountTransaction(exTrans);
					exAcc.setAccountBalance(exAcc.getAccountBalance() + transAmount);
					adao.updateAccount(exAcc, exAcc.getAccountId());
					ResponseStructure<Transaction> structure = new ResponseStructure<Transaction>();
					structure.setData(t);
					structure.setMessage("Transaction Success ,Amount Credited");
					structure.setStatus(HttpStatus.CREATED.value());
					exUser.setUserAccount(exAcc);
					udao.updateUser(exUser, exUser.getUserId());
					return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.CREATED);
				} else if (transType == 2) {
					if (exAcc.getAccountBalance() >= transAmount) {
						Transaction t = new Transaction();
						t.setStatus(TransactionType.DEBITED);
						t.setTransactionDate(LocalDate.now());
						t.setTransactionAmount(transAmount);
						tdao.saveTransaction(t);
						List<Transaction> exTrans = exAcc.getAccountTransaction();
						exTrans.add(t);
						exAcc.setAccountTransaction(exTrans);
						exAcc.setAccountBalance(exAcc.getAccountBalance() - transAmount);
						adao.updateAccount(exAcc, exAcc.getAccountId());
						ResponseStructure<Transaction> structure = new ResponseStructure<Transaction>();
						structure.setData(t);
						structure.setMessage("Transaction Success ,Amount debited");
						structure.setStatus(HttpStatus.CREATED.value());
						exUser.setUserAccount(exAcc);
						udao.updateUser(exUser, exUser.getUserId());
						return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.CREATED);
					}
					throw new InsufficientBalanceException();
				}
				throw new TransactionTypeException();
			}
			throw new AccountNotFoundException();
		}
		throw new UserCredentialException();

	}

	public ResponseEntity<ResponseStructure<List<Transaction>>> findAll(String uName, String uPass) {
		User exUser = udao.loginUser(uName, uPass);
		if (exUser != null) {
			if (exUser.getUserAccount() != null) {
				List<Transaction> transactions = exUser.getUserAccount().getAccountTransaction();
				ResponseStructure<List<Transaction>> structure = new ResponseStructure<>();
				structure.setData(transactions);
				structure.setMessage(uPass);
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.FOUND);
			}
			throw new AccountNotFoundException();
		}
		throw new UserCredentialException();
	}
}