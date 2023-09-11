package com.springboot.BankSpringApp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	@NotNull(message = "name can't be null")
	@NotBlank(message = "name can't be Blank")
	private String bankName;
	
	@NotNull(message = "Ifsc can't be null")
	@NotBlank(message = "Ifsc can't be Blank")
	
	private String bankIfsc;
	
	@NotNull(message = "Location can't be null")
	@NotBlank(message = "Location can't be Blank")
	private String bankLocation;
	
	@OneToMany
	private List<User> users;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Manager bankManager;

}