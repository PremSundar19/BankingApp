package com.springboot.BankSpringApp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.BankSpringApp.dto.Manager;
import com.springboot.BankSpringApp.repo.ManagerRepo;

@Repository
public class ManagerDao {

	@Autowired
	ManagerRepo repo;

	public Manager saveManager(Manager manager) {
		return repo.save(manager);
	}

	public Manager findManager(int managerId) {
		Optional<Manager> option = repo.findById(managerId);
		if (option.isPresent()) {
			return option.get();
		} else {
			return null;
		}
	}

	public Manager getByEmail(String managerEmail) {
		return repo.getByEmail(managerEmail);
	}

}
