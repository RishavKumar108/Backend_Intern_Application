package com.greenstitch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenstitch.entity.Customer;
import com.greenstitch.exception.CustomerNotFoundException;
import com.greenstitch.service.CustomerService;

@RestController
@RequestMapping("/greenstitch/customers")
public class GreenStitchController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer registeredCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<>(registeredCustomer,HttpStatus.CREATED);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<Customer> registerAdmin(@RequestBody Customer customer) {
        Customer registeredAdmin = customerService.registerAdmin(customer);
        return new ResponseEntity<>(registeredAdmin,HttpStatus.CREATED);
        		
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable String email) {
        Customer customer = customerService.findByEmail(email);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> findByPhone(@PathVariable String phone) {
        Customer customer = customerService.findByPhone(phone);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customer = customerService.findbyId(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomerAsAdmin(@PathVariable Long id) {
        Customer updatedCustomer = customerService.updateCustomerAsAdmin(id);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }
}


