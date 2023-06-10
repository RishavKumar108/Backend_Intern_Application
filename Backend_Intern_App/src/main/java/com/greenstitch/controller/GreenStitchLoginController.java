package com.greenstitch.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenstitch.entity.Customer;
import com.greenstitch.service.CustomerService;

@RestController
public class GreenStitchLoginController {

	
	@Autowired
	private CustomerService customerService;
	
	    @GetMapping("/login")
	    public ResponseEntity<Customer> loginCustomerHandler( Authentication auth) {
	        Customer cust  = customerService.findByEmail(auth.getName());
	        return new ResponseEntity<>(cust,HttpStatus.OK);
	    }

}
