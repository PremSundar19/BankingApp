package com.springboot.BankSpringApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.BankSpringApp.dto.Transaction;
import com.springboot.BankSpringApp.repo.TransactionRepo;

@Repository
public class TransactionDao {

	@Autowired
	TransactionRepo repo;

	public Transaction saveTransaction(Transaction transaction) {
		return repo.save(transaction);
	}

	public List<Transaction> findAll() {
		List<Transaction> transactions = repo.findAll();
		if (!transactions.isEmpty()) {
			return transactions;
		}
		return null;
	}

}
