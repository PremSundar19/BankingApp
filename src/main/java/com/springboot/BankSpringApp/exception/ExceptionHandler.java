package com.springboot.BankSpringApp.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.BankSpringApp.config.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> transactionTypeException(TransactionTypeException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Transaction Type Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerPasswordException(ManagerPasswordException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Manager Password Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerEmailException(ManagerEmailException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Manager Email Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerCredentialException(ManagerCredentialException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Manager Credential Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerNotFoundException(ManagerNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("manager not found with given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> insufficientBalanceException(InsufficientBalanceException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Insufficient Balance");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> accountNotFoundException(AccountNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Account not found with given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bankNotFoundException(BankNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Bank not found with given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userPasswordException(UserPasswordException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("User Password Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userCredentialException(UserCredentialException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("User Credential Wrong");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("user not found with given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String, String> map = new HashMap<String, String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String message = violation.getMessage();
			String field = violation.getPropertyPath().toString();
			map.put(message, field);
		}
		structure.setMessage("add proper details");
		structure.setData(map);
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		for (ObjectError error : allErrors) {
			String message = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			map.put(message, fieldName);
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
