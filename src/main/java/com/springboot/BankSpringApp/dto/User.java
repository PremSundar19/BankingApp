package com.springboot.BankSpringApp.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull
	@NotBlank
	private String userName;
	@Positive
	@Min(6000000000l)
	@Max(9999999999l)
	private long userContact;
	@NotNull
	@NotBlank
	private String userAddress;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$", message = "must contain at least one captain letter,one spl character,one number")
	private String userPassword;
	@OneToOne
	private Account userAccount;
}