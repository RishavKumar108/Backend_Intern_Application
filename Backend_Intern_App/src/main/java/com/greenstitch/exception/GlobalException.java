package com.greenstitch.exception;

import java.time.LocalDateTime;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetail> customerException(CustomerException ie, WebRequest req)  {
		
		ErrorDetail err= new ErrorDetail();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetail> customerNotFoundException(CustomerNotFoundException ie, WebRequest req)  {
		
		ErrorDetail err= new ErrorDetail();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	
	
@ExceptionHandler(NoHandlerFoundException.class)
public ResponseEntity<ErrorDetail> noHandlerFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
		

	ErrorDetail err=new ErrorDetail(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
				
}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> myExceptionHandler(Exception e,WebRequest req)  {
	

		ErrorDetail error = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> methodArgumentNotValidException(MethodArgumentNotValidException m,WebRequest req)  {
			

		ErrorDetail err=new ErrorDetail(LocalDateTime.now(), m.getMessage(), req.getDescription(false));

		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}
}
