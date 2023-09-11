package com.springboot.BankSpringApp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message = "user not found";
}
