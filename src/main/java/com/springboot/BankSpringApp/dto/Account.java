package com.springboot.BankSpringApp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;

	private long accountNumber;
	private double accountBalance;
	private AccountType accountType;
	@OneToMany
	private List<Transaction> accountTransaction;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;

}