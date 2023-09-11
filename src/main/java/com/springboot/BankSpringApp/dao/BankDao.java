package com.springboot.BankSpringApp.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.BankSpringApp.dto.Bank;
import com.springboot.BankSpringApp.repo.BankRepo;

@Repository
public class BankDao {
	@Autowired
	BankRepo repo;
	public Bank saveBank(Bank bank) {
		return repo.save(bank);
	}
	public Bank findBank(int bankId) {
		Optional<Bank> optional = repo.findById(bankId);
		if (optional.isPresent()) 
			return optional.get();
		return null;
	}
	public Bank deleteBank(int bankId) {
		Bank exBank = findBank(bankId);
		if (exBank != null) {
			repo.delete(exBank);
			return exBank;
		}
		return null;
	}
	public Bank updateBank(Bank bank, int bankId) {
		Bank exBank = findBank(bankId);
		if (exBank != null) {
			bank.setBankId(bankId);
			return repo.save(bank);
		}
		return null;
	}
	public List<Bank> findAll() {
		return repo.findAll();
	}
}
