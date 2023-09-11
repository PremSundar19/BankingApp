package com.springboot.BankSpringApp.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.BankSpringApp.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	@Query("select t from Transaction t where t.transactionDate between ?1 and  ?2 ")
	public List<Transaction> betweenDates(LocalDate d1, LocalDate d2);
}
