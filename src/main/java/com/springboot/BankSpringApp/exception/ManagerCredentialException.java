package com.springboot.BankSpringApp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerCredentialException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message = "check manager email & manager password";

}
