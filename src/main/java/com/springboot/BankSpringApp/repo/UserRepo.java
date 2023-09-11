package com.springboot.BankSpringApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.BankSpringApp.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userName=?1 and u.userPassword=?2")
	public User loginUser(String name, String password);

	@Query("select u from User u where u.userName=?1")
	public User loginUser(String name);

}
