package com.springboot.BankSpringApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.BankSpringApp.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer> {

}
