package com.springboot.BankSpringApp.dao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.BankSpringApp.dto.Account;
import com.springboot.BankSpringApp.repo.AccountRepo;
@Repository
public class AccountDao {
	@Autowired
	AccountRepo repo;
	public Account saveAccount(Account account) {
		return repo.save(account);
	}
	public Account findAccount(int accountId) {
		Optional<Account> optional = repo.findById(accountId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public Account deleteAccount(int accountId) {
		Account exAcount = findAccount(accountId);
		if (exAcount != null) {
			repo.delete(exAcount);
			return exAcount;
		}
		return null;
	}
	public Account updateAccount(Account acc, int accountId) {
		Account exAcount = findAccount(accountId);
		if (exAcount != null) {
			repo.save(acc);
			return exAcount;
		}
		return null;
	}
}
