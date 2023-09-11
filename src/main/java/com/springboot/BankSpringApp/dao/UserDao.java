package com.springboot.BankSpringApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.BankSpringApp.dto.User;
import com.springboot.BankSpringApp.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	UserRepo repo;
	public User saveUser(User user) {
		return repo.save(user);
	}
	public User findUser(int userId) {
		Optional<User> optional = repo.findById(userId);
		if (optional.isPresent()) 
			return optional.get();
		return null;
	}
	public User deleteUser(int userId) {
		User exUser = findUser(userId);
		if (exUser != null) {
			repo.delete(exUser);return exUser;
		}return null;
	}
	public List<User> findAll() {
		return repo.findAll();
	}
	public User updateUser(User user, int userId) {
		User exUser = findUser(userId);
		if (exUser != null) {
			repo.save(user);return user;
		}	return null;
	}
	public User loginUser(String uName, String uPass) {
		return repo.loginUser(uName, uPass);
	}
}
