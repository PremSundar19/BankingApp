package com.springboot.BankSpringApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.BankSpringApp.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.managerEmail=?1")
	public Manager getByEmail(String managerEmail);

	@Query("select m from Manager m where m.managerEmail=?1 and m.managerPassword=?2")
	public Manager login(String email, String password);
}
