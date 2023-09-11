package com.springboot.BankSpringApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.BankSpringApp.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
