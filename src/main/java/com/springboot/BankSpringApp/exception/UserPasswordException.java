package com.springboot.BankSpringApp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message = "user password wrong";
}
